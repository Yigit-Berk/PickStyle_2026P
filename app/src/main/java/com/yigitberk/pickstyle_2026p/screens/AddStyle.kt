package com.yigitberk.pickstyle_2026p.screens

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yigitberk.pickstyle_2026p.R
import com.yigitberk.pickstyle_2026p.ui.theme.PickStyle_2026PTheme

@Composable
fun AddList(){
    Column(
        modifier = Modifier.fillMaxSize().
        background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            contentDescription = "Fotoğraf Çek",
            painter = painterResource(R.drawable.take_photo),
            modifier =  Modifier.border(5.dp, MaterialTheme.colorScheme.surface, shape = RectangleShape)
        )
        Spacer(Modifier.size(20.dp))
        TextField(
            value = "",
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
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    PickStyle_2026PTheme {
        AddList()
    }
}