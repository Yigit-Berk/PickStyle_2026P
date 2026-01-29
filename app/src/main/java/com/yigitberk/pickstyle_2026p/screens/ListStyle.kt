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
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.yigitberk.pickstyle_2026p.model.Item
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme


/*Genel Liste Görünümü ve floating button*/
@Composable
fun StyleList(itemList: List<Item> , navController: NavController) {
    Scaffold(
        Modifier.background(MaterialTheme.colorScheme.primary)
            //.padding(40.dp,60.dp)
        ,topBar = {}
        ,floatingActionButtonPosition = FabPosition.End
        ,floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("Add_Style_Screen")//bu ekrana git
            }) {
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
                    //var boyut = 0 // geçici değişken

                    if(itemList.count() > 0) {
                        items(itemList) {
                            ListRow(it)
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
            "Stil eklemek için \"+\" tuşuna dokunun",
            color = MaterialTheme.colorScheme.primaryContainer,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

//listedeki tek bir item görünümü
@Composable
fun ListRow(item: Item){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalDivider(thickness = 2.dp)//MaterialTheme component
        Text(
            text = item.itemName,
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
        //StyleList(emptyList())
    }
}