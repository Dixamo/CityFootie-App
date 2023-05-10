package com.example.cityfootie_compose.ui.screens.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import androidx.navigation.NavController
import com.example.cityfootie_compose.R
import com.example.cityfootie_compose.navigation.AppScreens
import com.example.cityfootie_compose.util.toFloat

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    Scaffold(topBar = {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    }) {
        BodyContent(navController)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.app_name)
        )
    }
}

@Composable
fun BodyContent(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()){
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }

    val isButtonEnabled: Boolean by loginViewModel.isButtonEnabled.observeAsState(initial = false)
    val isError: Boolean by loginViewModel.isError.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val loading: Boolean by loginViewModel.loading.observeAsState(initial = false)

    if (loading == true) {
        CircularProgressIndicator()
    }

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

        EmailField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp)
                .focusRequester(focusRequester),
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
            keyboardType = KeyboardType.Text,
            onChange = { loginViewModel.onEmailChange(it)  }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        PasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 24.dp)
                .focusRequester(focusRequester),
            label = "Password",
            placeholder = "Password",
            text = loginViewModel.password,
            imeAction = ImeAction.Done,
            isEnabled = true,
            keyboardType = KeyboardType.Password,
            keyBoardActions = KeyboardActions(
                onDone = {}
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                SeePassword(
                    isVisible = passwordVisible, setVisible = { it ->
                        passwordVisible = it
                    }
                ) },
                onChange = { loginViewModel.onPasswordChange(it) }
            )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                      loginViewModel.getUser()
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Iniciar sesión")
        }

        Text(text = "Correo o contraseña incorrectas", modifier = Modifier.alpha(isError.toFloat()))

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
                    navController.navigate(route = AppScreens.CreateAccountScreen.route)
                }
            )
        }
    }
}

@Composable
fun EmailField(
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

@Composable
fun PasswordField(
    modifier: Modifier,
    text: String,
    label: String,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = Modifier.height(90.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { onChange(it) },
            trailingIcon = trailingIcon,
            textStyle = TextStyle(fontSize = 18.sp),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
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
fun SeePassword(isVisible: Boolean, setVisible:(Boolean)->Unit){
    if (isVisible) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.clickable {
                setVisible(false)
            },
            tint = MaterialTheme.colors.primary
        )
    } else {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.clickable {
                setVisible(true)
            },
            tint = MaterialTheme.colors.primary
        )
    }
}