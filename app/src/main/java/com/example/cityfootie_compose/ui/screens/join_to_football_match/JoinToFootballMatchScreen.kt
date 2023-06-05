package com.example.cityfootie_compose.ui.screens.join_to_football_match

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.screens.create_football_match.CreateFootballMatchViewModel
import com.example.cityfootie_compose.ui.screens.login.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JoinToFootballMatchScreen(
    email: String,
    latitude: String,
    longitude: String,
    goBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    goBack()
                })
        }
    }) {
        BodyContent(email, latitude, longitude, goBack)
    }
}

@Composable
fun BodyContent(
    email: String,
    latitude: String,
    longitude: String,
    goBack: () -> Unit,
    joinToFootballMatchViewModel: JoinToFootballMatchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            "Unirse al Partido",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.padding(10.dp))

        //val isButtonEnabled: Boolean by joinToFootballMatchViewModel.isButtonEnabled.observeAsState(initial = false)

        Button(
            onClick = {
                joinToFootballMatchViewModel.putFootballMatch(email, latitude.toDouble(), longitude.toDouble())
                goBack()
            },
            //enabled = isButtonEnabled
        ) {
            Text(text = "Unirse partido")
        }
    }
}

@Composable
fun DataField(
    modifier: Modifier,
    text: String,
    label: String,
    placeholder: String,
    onChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    Column(modifier = Modifier.height(90.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { onChange(it) },
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
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