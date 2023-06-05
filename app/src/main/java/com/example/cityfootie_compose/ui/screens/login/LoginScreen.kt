package com.example.cityfootie_compose.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.cityfootie_compose.util.toFloat
import okhttp3.internal.wait
import java.lang.Thread.sleep

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    goUserScreen: (String, String) -> Unit,
    goRegisterScreen: () -> Unit
) {
    BodyContent(goUserScreen, goRegisterScreen)
}

@Composable
fun BodyContent(
    goUserScreen: (String, String) -> Unit,
    goRegisterScreen: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    /*val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)*/
    var isLoading: Boolean = loginViewModel.isLoading
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

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        EmailField(
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

        var passwordVisible by remember { mutableStateOf(false) }
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
                    isVisible = passwordVisible, setVisible = { it ->
                        passwordVisible = it
                    }
                )
            },
            onChange = { loginViewModel.onPasswordChange(it) }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        var player = loginViewModel.player
        var isCompleted = loginViewModel.isCompleted
        val isButtonEnabled: Boolean by loginViewModel.isButtonEnabled.observeAsState(initial = false)
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
                    goUserScreen(player.email!!, player.password!!)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        var isError: Boolean = loginViewModel.isError
        Text(
            text = "Correo o contraseña incorrectas",
            modifier = Modifier.alpha(isError.toFloat()),
            color = MaterialTheme.colors.error
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Row() {
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

@Composable
fun EmailField(
    modifier: Modifier,
    icon: ImageVector,
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
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "User Icon"
                )
            },
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