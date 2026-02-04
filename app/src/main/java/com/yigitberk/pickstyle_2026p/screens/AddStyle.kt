package com.yigitberk.pickstyle_2026p.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.EditText
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.collection.emptyObjectList
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.yigitberk.pickstyle_2026p.R
import com.yigitberk.pickstyle_2026p.model.Item
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme
import okio.ByteString.Companion.toByteString
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun AddList(saveFunction: (item: Item) -> Unit){

    //gelen veriyi hatırlamak ve işlemek için remember
    val itemName = remember {
        mutableStateOf("(İsimsiz stil)")
    }
    val categoryName = remember {
        mutableStateOf("(Kategori seçilmedi)")
    }
    //var photoToImageUri by remember { mutableStateOf<Uri?>(null) }
    var context = LocalContext.current

    // Çekilen fotoğrafın URI'sini burada tutacağız
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().
        background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Stilini çekmek için dokun",
            color = MaterialTheme.colorScheme.surface
        )
        /*Camera X ve kontroller*/
        /* //Demo
        Image(
            contentDescription = "Fotoğraf Çek",
            painter = painterResource(R.drawable.take_photo),
            modifier =  Modifier.border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
        )
         */
        StyleCamera() //son hali

        Spacer(Modifier.size(20.dp))
        TextField(
            value = itemName.value,
            onValueChange = {
                itemName.value = it
            },
            placeholder = { Text("Stiline bir isim ver")},
            colors = TextFieldDefaults.colors (
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = MaterialTheme.colorScheme.surfaceDim,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.surface
            )
        )
        Spacer(Modifier.size(20.dp))
        RadioButtonSingleSelection(categoryName = categoryName)
        Spacer(Modifier.size(20.dp))

        Button(border = BorderStroke(2.dp,MaterialTheme.colorScheme.surface),onClick = {

            //yukarıda oluşturduğumuz selectedImage değişkenimiz
            //bize bir context lazım olacağı (fonksiyonumuz resizeImage için) için yine yukarıda tanımladık
            val imageByteArray = capturedImageUri?.let {
                resizeImage(
                    context = context,
                    uri = it,
                    maxWidth = 600,
                    maxHeight = 400
                )

            } ?: ByteArray(0) // null ise boş bir byteArray oluştursun

            val itemToInsert = Item(
                itemName = itemName.value,
                category = categoryName.value,
                image = imageByteArray //değişkenimizi verdik boş byteArray yerine
                //image = ByteArray(1) //boş byte array
            )
            saveFunction(itemToInsert)
            println("kayıt sonrası kategori değeri: " + categoryName.value)
            println("kayıt sonrası image değeri: " + imageByteArray.toByteString() + " and \n" + imageByteArray.toString())

        }) {
            Text("Kaydet")
        }

    }
}

@Composable
fun RadioButtonSingleSelection(
    modifier: Modifier = Modifier,
    categoryName: MutableState<String>
) {

    //val categoryName = remember { mutableStateOf("") }

    //Kategoriler
    val radioOptions = listOf("Gündelik","Sportif", "İş","Tatil","Minimalist")

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }

    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(
        modifier.selectableGroup()
        .padding(60.dp,20.dp)
            .border(2.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        //selected true ise radiobutton içi dolu olur
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)//"seçili olan artık bu text değeridir" diye bildirir
                            categoryName.value = text //remember
                            println("Seçilen RadioButton: " + categoryName.value)
                                  },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) //"seçili olan artık bu text değeridir" diye bildirir
                        categoryName.value = text //remember
                        println("Seçilen RadioButton: " + categoryName.value)
                              }, // null recommended for accessibility with screen readers
                    colors = RadioButtonColors(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface)
                    )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp),
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

/*Camera X kontrolleri ve görünüm davranışları*/
@Composable
fun StyleCamera(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    // Çekilen fotoğrafın URI'sini burada tutacağız
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Kameranın açık olup olmadığını kontrol eden state
    var showCamera by remember { mutableStateOf(false) }

    // İzin istemek için launcher'ı tanımlarız
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showCamera = true // İzin verildiyse kamerayı aç
        } else {
            // Kullanıcı reddetti, burada bir Toast mesajı gösterebilirsin
        }
    }

    if (showCamera) {
        CameraView(
            onImageCaptured = {uri ->
                capturedImageUri = uri
                showCamera = false //fotoğraf çekilince kamerayı kapat
            },
            onError = { Log.e("Camera","Hata: ${it.message}")}
        )
    } else {
            Image(
                painter = if (capturedImageUri != null) {
                    rememberAsyncImagePainter(capturedImageUri) // Çekilen fotoğraf
                } else {
                    painterResource(R.drawable.take_photo) // Varsayılan ikon
                },
                contentDescription = "Fotoğraf Çek",
                modifier = Modifier
                    .size(150.dp)
                    .border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
                    .clickable {
                        // Tıklanınca kamerayı aç
                        val permissionCheck = ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        )

                        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                            showCamera = true // İzin zaten varsa aç
                        } else {
                            permissionLauncher.launch(Manifest.permission.CAMERA) // Yoksa iste
                        }
                        //showCamera = true
                    },
                contentScale = ContentScale.Crop
            )

    }
}

/*kamera ekranı için gerekli fonksiyonlar*/
// Kamera sağlayıcısını asenkron olarak almak için
suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

// Gerçek çekim işlemini yapan fonksiyon
private fun takePhoto(
    imageCapture: ImageCapture,
    context: Context,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val photoFile = File(
        context.cacheDir, // Geçici olarak cache'e kaydediyoruz
        "${System.currentTimeMillis()}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    //camera x: takePicture
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                onImageCaptured(savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }
        }
    )
}

/*kamera açıldığında çalışacak yapı*/
@Composable
fun CameraView(
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    val lensFacing = CameraSelector.LENS_FACING_BACK //arka kamera seçilir
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = androidx.camera.core.Preview.Builder().build() //görüntüyü önizleme için
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() } //denklanşör + dosyaya yazma için
    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build() //arka  veya ön kamera için

    // Kamera başlatma işlemi
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider() // Yardımcı fonksiyon yukarıda
        cameraProvider.unbindAll() // Önceki kamera oturumlarını temizler
        cameraProvider.bindToLifecycle( //Kamerayı içinde bulunduğu lifecycle'a bağlar. Ekran kapandığında kamera otomatik olarak durur(pil optimizasyonu)
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider) //preview için bir surface ayarlar (kullanıcı canlı olarak kameranın ne gördüğünü görür)
    }

    // Kamera UI
    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        // Canlı Kamera Görüntüsü
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        // Fotoğraf Çekme Butonu
        Button(
            modifier = Modifier.padding(bottom = 50.dp),
            onClick = {
                takePhoto(
                    imageCapture = imageCapture,
                    context = context,
                    onImageCaptured = onImageCaptured,
                    onError = onError
                )
            }
        ) {
            Text("Fotoğrafı Çek")
        }
    }
}


/* uri'yi alıp byte'a çeviriyoruz 1 ve 0'lara çeviriyoruz */
fun resizeImage(context: Context, uri: Uri, maxWidth: Int, maxHeight: Int) : ByteArray? {

    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)

        val ratio =
            originalBitmap.width.toFloat() / originalBitmap.height.toFloat() //width: 300 height:100 diyelim ratio = 3
        var width = maxWidth //width 150'ye düşecekse height da mantıken 50'ye düşmeli
        var height = (width / ratio).toInt() //150 / 3 = 50 (yani doğru oranda resim küçültülüyor)

        //Height değerimiz maxHeight'dan büyükse küçültmemiz gerekiyor.
        if (height > maxHeight) { //height: 50 > maxHeight: 40
            height = maxHeight //height = 40
            width =
                (height * ratio).toInt() // 40*3 = 120 (yani ratio ile orantılı olarak width değerini de büyüttük)
        }

        //küçültülmüş bitmap yani
        val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)

        val byteArrayOutputStream = ByteArrayOutputStream()
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        byteArrayOutputStream.toByteArray() //bunu return edebiliriz veya return try içinde alabiliriz
    }
    catch (e: Exception)
    {
        e.printStackTrace()
        null // null döndür.
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PickStyle_2026PTheme {
        //AddList()
    }
}