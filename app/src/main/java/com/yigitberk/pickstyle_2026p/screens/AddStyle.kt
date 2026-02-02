package com.yigitberk.pickstyle_2026p.screens

import android.net.Uri
import android.widget.EditText
import androidx.collection.emptyObjectList
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yigitberk.pickstyle_2026p.R
import com.yigitberk.pickstyle_2026p.model.Item
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme

@Composable
fun AddList(saveFunction: (item: Item) -> Unit){

    //gelen veriyi hatırlamak için remember
    val itemName = remember {
        mutableStateOf("")
    }
    val categoryName = remember {
        mutableStateOf("")
    }
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var context = LocalContext.current

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
        Image(
            contentDescription = "Fotoğraf Çek",
            painter = painterResource(R.drawable.take_photo),
            modifier =  Modifier.border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
        )
        Spacer(Modifier.size(20.dp))
        TextField(
            value = itemName.value,
            onValueChange = { },
            placeholder = { Text("Stiline bir isim ver")},
            colors = TextFieldDefaults.colors (
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                unfocusedTextColor = MaterialTheme.colorScheme.surfaceDim,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary
            )
        )
        Spacer(Modifier.size(20.dp))
        RadioButtonSingleSelection()
        Spacer(Modifier.size(20.dp))

        Button(border = BorderStroke(2.dp,MaterialTheme.colorScheme.surface),onClick = {

        }) {
            Text("Kaydet")
        }

    }
}

@Composable
fun RadioButtonSingleSelection(modifier: Modifier = Modifier) {
    var categoryName = remember {
        mutableStateOf("")
    }

    //Kategoriler
    val radioOptions = listOf("Gündelik","Sportif", "İş","Tatil","Minimalist")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
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
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
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
                    onClick = { onOptionSelected(text)
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


@Preview(showBackground = true)
@Composable
fun Preview() {
    PickStyle_2026PTheme {
        //AddList()
    }
}