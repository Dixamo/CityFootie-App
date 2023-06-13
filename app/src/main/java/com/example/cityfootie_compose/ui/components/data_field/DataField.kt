package com.example.cityfootie_compose.ui.components.data_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataField(
    modifier: Modifier,
    text: String,
    label: String,
    placeholder: String,
    onChange: (String) -> Unit,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    keyBoardActions: KeyboardActions,
    isEnabled: Boolean = true
) {
    Column(modifier = Modifier.height(90.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { onChange(it) },
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyBoardActions,
            enabled = isEnabled,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
                disabledTextColor = Color.Black
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(fontSize = 18.sp, color = Color.LightGray)
                )
            },
            label = {
                Text(text = label, fontSize = 14.sp)
            }
        )
    }
}