package com.yigitberk.pickstyle_2026p

import android.R
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yigitberk.pickstyle_2026p.screens.AddList
import com.yigitberk.pickstyle_2026p.screens.StyleList
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme
import com.yigitberk.pickstyle_2026p.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    /*-------viewmodel tanımlaması-----*/
    private val viewModel: ItemViewModel by viewModels<ItemViewModel>() //ileri seviye delegate yöntemi ile viewmodel'i init ettik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()//dependency eklendi

            PickStyle_2026PTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                     */
                    Box(Modifier.padding(innerPadding)){
                        NavHost(navController, startDestination = "ListStyleName"){

                            /*ilk ekranımız*/
                            composable("ListStyleName"){

                                viewModel.getItemList()//tüm liste gelsin
                                val itemList by remember { // Hatırla
                                    viewModel.itemList
                                }
                                StyleList(itemList, navController)

                            }
                            /*ikinci ekleme ekranımız*/
                            composable ("Add_Style_Screen"){
                                AddList {item ->
                                    //item'ı save et ve list_screen'e dön
                                    viewModel.saveItem(item)
                                    navController.navigate("ListStyleName")
                                }
                            }
                        }

                    }

                    //test için
                    //StyleList(emptyList())
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PickStyle_2026PTheme {
        //StyleList()
    }
}