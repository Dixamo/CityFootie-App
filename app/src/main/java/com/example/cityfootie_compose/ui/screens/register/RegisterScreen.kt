package com.example.cityfootie_compose.ui.screens.register

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.components.data_field.DataField
import com.example.cityfootie_compose.ui.components.password_field.PasswordField
import com.example.cityfootie_compose.ui.components.password_field.SeePassword
import com.example.cityfootie_compose.ui.components.user_field.UserField
import com.example.cityfootie_compose.util.toFloat

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    goBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    goBack()
                })
        }
    }) {
        BodyContent(goBack)
    }
}

@Composable
fun BodyContent(
    goBack: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            "Crear cuenta",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.padding(0.dp))

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        //NOMBRE
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .focusRequester(focusRequester),
            label = "Nombre",
            placeholder = "Nombre",
            text = registerViewModel.name,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Text,
            onChange = { registerViewModel.onNameChange(it) }
        )

        //APELLIDOS
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .focusRequester(focusRequester),
            label = "Apellidos",
            placeholder = "Apellidos",
            text = registerViewModel.surnames,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Text,
            onChange = { registerViewModel.onSurnamesChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //CORREO ELECTRÓNICO
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .focusRequester(focusRequester),
            label = "E-Mail",
            placeholder = "E-Mail",
            text = registerViewModel.email,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Email,
            onChange = { registerViewModel.onEmailChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //DORSAL
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .focusRequester(focusRequester),
            label = "Dorsal",
            placeholder = "Dorsal",
            text = registerViewModel.number,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number,
            onChange = {
                registerViewModel.onNumberChange(it)
            }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //USERNAME
        UserField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp)
                .focusRequester(focusRequester),
            icon = Icons.Default.Person,
            label = "Username",
            placeholder = "Username",
            text = registerViewModel.username,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Text,
            onChange = { registerViewModel.onUsernameChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //PASSWORD
        var passwordVisible by remember { mutableStateOf(false) }
        PasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .focusRequester(focusRequester),
            icon = Icons.Default.Lock,
            label = "Password",
            placeholder = "Password",
            text = registerViewModel.password,
            imeAction = ImeAction.Done,
            isEnabled = true,
            keyboardType = KeyboardType.Password,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                SeePassword(
                    isVisible = passwordVisible, setVisible = {
                        passwordVisible = it
                    }
                )
            },
            onChange = { registerViewModel.onPasswordChange(it) }
        )

        Spacer(modifier = Modifier.padding(1.dp))

        val isButtonEnabled: Boolean by registerViewModel.isButtonEnabled.observeAsState(initial = false)

        val isError: Boolean = registerViewModel.isError
        Text(
            text = "Nombre de Usuario o correo existentes",
            modifier = Modifier.alpha(isError.toFloat()),
            color = MaterialTheme.colors.error
        )

        val response = registerViewModel.response
        val isCompleted = registerViewModel.isCompleted
        LaunchedEffect(response) {
            if (response != null) {
                if (isCompleted) {
                    goBack()
                }
            }
        }

        //BOTÓN
        Button(
            onClick = {
                registerViewModel.postUser()
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Crear cuenta")
        }
    }
}
