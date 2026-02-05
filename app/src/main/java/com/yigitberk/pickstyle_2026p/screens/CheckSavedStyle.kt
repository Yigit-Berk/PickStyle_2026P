package com.yigitberk.pickstyle_2026p.screens

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yigitberk.pickstyle_2026p.MainActivity
import com.yigitberk.pickstyle_2026p.R
import com.yigitberk.pickstyle_2026p.model.Item
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme

@Composable
fun ListSavedStyle(item: Item?, deleteFunction: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            "Stilim",
            color = MaterialTheme.colorScheme.surface ,
            style = MaterialTheme.typography.displaySmall,
            textDecoration = TextDecoration.Underline
        )
        Spacer(Modifier.size(10.dp))
        val imageBitmap = item?.image?.let { byteArray ->
            BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)?.asImageBitmap()
        }
        Image(
            contentDescription = "Kayıtlı Stil : " + item?.itemName,
            bitmap = imageBitmap ?: ImageBitmap.imageResource(R.drawable.take_photo),
            modifier =  Modifier
                .border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
                .size(300.dp, 400.dp)
        )
        Spacer(Modifier.size(20.dp))
        Text(
            text = item?.itemName ?: "(Stil Adı Bulunamadı)",
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.size(10.dp))
        Text(
             text = item?.category ?: "(Stil Kategorisi Bulunamadı)",
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.size(10.dp))
        Button(onClick = {
            deleteFunction()
            Toast.makeText(context, "Stil silindi", Toast.LENGTH_LONG).show()
        },
            Modifier.padding(20.dp),
            colors = ButtonColors(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                "Kaldır",
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewStyle() {
    PickStyle_2026PTheme {
        //ListSavedStyle(null)
    }
}