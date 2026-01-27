package com.yigitberk.pickstyle_2026p.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme


/*Genel Liste Görünümü ve floating button*/
@Composable
fun StyleList(modifier: Modifier = Modifier) {
    Scaffold(
        Modifier.background(MaterialTheme.colorScheme.primary)
            //.padding(40.dp,60.dp)
        ,topBar = {}
        ,floatingActionButtonPosition = FabPosition.End
        ,floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
        ,content = {
                LazyColumn(
                    contentPadding = it,
                    //Modifier.padding(40.dp, 60.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                        .fillMaxSize().padding(40.dp,60.dp)
                ) {
                    //tek bir item da eklenebiliyor:
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Stillerim",
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                    var boyut = 0 // geçici değişken

                    if(boyut > 0) {
                        items(boyut) {
                            ListRow()
                        }
                    }
                    else{
                        item {
                            EmptyText()
                        }
                    }


                }
        }

    )

}

//kullanıcıyı bilgilendirme için
@Composable
fun EmptyText(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Stil eklemek için \"+\" tuşuna basın ",
            color = MaterialTheme.colorScheme.primaryContainer,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun ListRow(){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalDivider(thickness = 2.dp)//MaterialTheme component
        Text(
            "Gece Kuşu",
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.displaySmall
        )
        HorizontalDivider(thickness = 2.dp)//MaterialTheme component
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PickStyle_2026PTheme {
        StyleList()
    }
}