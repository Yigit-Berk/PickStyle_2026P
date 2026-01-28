package com.yigitberk.pickstyle_2026p.screens

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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yigitberk.pickstyle_2026p.R
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme

@Composable
fun ListSavedStyle() {
    Column(
        modifier = Modifier.fillMaxSize().
        background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(
            "Stilim",
            color = MaterialTheme.colorScheme.surface ,
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.size(20.dp))
        Text(
            "(Gece Kuşu)",
            color = MaterialTheme.colorScheme.surface
        )
        Spacer(Modifier.size(20.dp))
        Image(
            contentDescription = "Fotoğraf Çek",
            painter = painterResource(R.drawable.take_photo),
            modifier =  Modifier.border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
                .size(300.dp,400.dp)
        )
        Spacer(Modifier.size(20.dp))
        Button(onClick = {
            //yukarıda oluşturduğumuz selectedImage değişkenimiz
            //bize bir context lazım olacağı (fonksiyonumuz resizeImage için) için yine yukarıda tanımladık
            /*
            val itemToInsert = Item(
                itemName = itemName.value,
                storeName = storeName.value,
                price = price.value,
                image = imageByteArray //değişkenimizi verdik boş byteArray yerine
                //image = ByteArray(1) //boş byte array
            )
            saveFunction(itemToInsert)
             */
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
        ListSavedStyle()
    }
}