package com.example.cityfootie_compose.ui.components.password_field

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PasswordField(
    modifier: Modifier,
    icon: ImageVector,
    text: String,
    label: String,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    keyBoardActions: KeyboardActions,
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.height(90.dp)) {
        OutlinedTextField(
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Lock Icon"
                )
            },
            value = text,
            onValueChange = { onChange(it) },
            trailingIcon = trailingIcon,
            textStyle = TextStyle(fontSize = 18.sp),
            visualTransformation = visualTransformation,
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

@Composable
fun SeePassword(isVisible: Boolean, setVisible: (Boolean) -> Unit) {
    if (isVisible) {
        Icon(
            imageVector = Icons.Default.RemoveRedEye,
            contentDescription = null,
            modifier = Modifier.clickable {
                setVisible(false)
            },
            tint = MaterialTheme.colors.primary
        )
    } else {
        Icon(
            imageVector = Icons.Default.RemoveRedEye,
            contentDescription = null,
            modifier = Modifier.clickable {
                setVisible(true)
            },
            tint = MaterialTheme.colors.primary
        )
    }
}