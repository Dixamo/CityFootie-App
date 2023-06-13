package com.example.cityfootie_compose.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.components.password_field.PasswordField
import com.example.cityfootie_compose.ui.components.password_field.SeePassword
import com.example.cityfootie_compose.ui.components.user_field.UserField
import com.example.cityfootie_compose.util.toFloat

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    goUserScreen: (String) -> Unit,
    goRegisterScreen: () -> Unit
) {
    BodyContent(goUserScreen, goRegisterScreen)
}

@Composable
fun BodyContent(
    goUserScreen: (String) -> Unit,
    goRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val isLoading: Boolean = loginViewModel.isLoading

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var passwordVisible by remember { mutableStateOf(false) }

    val isError: Boolean = loginViewModel.isError
    val player = loginViewModel.player
    val isCompleted = loginViewModel.isCompleted

    val isButtonEnabled: Boolean by loginViewModel.isButtonEnabled.observeAsState(initial = false)

    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    Spacer(modifier = Modifier.padding(10.dp))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "BIENVENIDO",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "¡INICIA SESION!",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.padding(25.dp))

        UserField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp)
                .focusRequester(focusRequester),
            icon = Icons.Default.Person,
            label = "E-Mail",
            placeholder = "E-Mail",
            text = loginViewModel.email,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Email,
            onChange = { loginViewModel.onEmailChange(it) }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        PasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp)
                .focusRequester(focusRequester),
            icon = Icons.Default.Lock,
            label = "Password",
            placeholder = "Password",
            text = loginViewModel.password,
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
            onChange = { loginViewModel.onPasswordChange(it) }
        )

        Text(
            text = "Correo o contraseña incorrectas",
            modifier = Modifier.alpha(isError.toFloat()),
            color = MaterialTheme.colors.error
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                loginViewModel.getPlayer()
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Iniciar sesión")
        }

        LaunchedEffect(isLoading) {
            if (isCompleted) {
                if (player != null) {
                    Log.d("player", player.name.toString())
                    goUserScreen(player.email!!)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row {
            Text(
                text = "¿No tienes cuenta? "
            )

            ClickableText(
                text = AnnotatedString("¡Créate una!"),
                style = TextStyle(
                    color = MaterialTheme.colors.primary
                ),
                onClick = {
                    goRegisterScreen()
                }
            )
        }
    }
}
