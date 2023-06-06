package com.example.cityfootie_compose.ui.screens.modify

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.screens.register.UsernameField

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ModifyScreen(
    goBack: () -> Unit,
    email: String,
    modifyViewModel: ModifyViewModel = hiltViewModel()
) {
    var isCompleted = modifyViewModel.isCompleted
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    isCompleted = false
                    goBack()
                })
        }
    }) {
        BodyContent(goBack, email, isCompleted)
    }
}

@Composable
fun BodyContent(
    goBack: () -> Unit,
    email: String,
    isCompleted: Boolean,
    modifyViewModel: ModifyViewModel = hiltViewModel()
) {
    var player = modifyViewModel.player

    if (!isCompleted) {
        LaunchedEffect(Unit) {
            modifyViewModel.getPlayer(email)
        }
    }

    if (isCompleted) {
        if (player != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Modificación de Datos",
                    fontSize = 35.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                val focusRequester = remember { FocusRequester() }
                val focusManager = LocalFocusManager.current

                DataField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .padding(top = 24.dp)
                        .focusRequester(focusRequester),
                    label = "Username",
                    placeholder = "Username",
                    text = modifyViewModel.username,
                    imeAction = ImeAction.Next,
                    isEnabled = true,
                    keyBoardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardType = KeyboardType.Text,
                    onChange = { modifyViewModel.onUsernameChange(it) }
                )

                //NOMBRE
                DataField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .padding(top = 0.dp)
                        .focusRequester(focusRequester),
                    label = "Nombre",
                    placeholder = "Nombre",
                    text = modifyViewModel.name,
                    imeAction = ImeAction.Next,
                    isEnabled = true,
                    keyBoardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardType = KeyboardType.Text,
                    onChange = { modifyViewModel.onNameChange(it) }
                )

                //APELLIDOS
                DataField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .padding(top = 0.dp)
                        .focusRequester(focusRequester),
                    label = "Apellidos",
                    placeholder = "Apellidos",
                    text = modifyViewModel.surnames,
                    imeAction = ImeAction.Next,
                    isEnabled = true,
                    keyBoardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardType = KeyboardType.Text,
                    onChange = { modifyViewModel.onSurnameChange(it) }
                )

                //DORSAL
                DataField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .padding(top = 0.dp)
                        .focusRequester(focusRequester),
                    label = "Dorsal",
                    placeholder = "Dorsal",
                    text = modifyViewModel.number,
                    imeAction = ImeAction.Next,
                    isEnabled = true,
                    keyBoardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardType = KeyboardType.Number,
                    onChange = { modifyViewModel.onNumberChange(it) }
                )


                Row() {
                    val isButtonEnabled: Boolean by modifyViewModel.isButtonEnabled.observeAsState(initial = false)
                    Button(
                        onClick = {
                            modifyViewModel.updatePlayer(email)
                            goBack()
                        },
                        enabled = isButtonEnabled
                    ) {
                        Text(
                            text = "Modificar",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
    var isLoading: Boolean = modifyViewModel.isLoading
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
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