package com.example.cityfootie_compose.ui.screens.footballMatch

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.example.cityfootie_compose.ui.screens.register.DataField
import com.example.cityfootie_compose.ui.screens.register.RegisterViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FootballMatchScreen(
    goBack: () -> Unit, ) {
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
        BodyContent(goBack)
    }
}

@Composable
fun BodyContent(
    goBack: () -> Unit,
    footBallMatchViewModel: FootBallMatchViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            "Crear Partido",
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.padding(10.dp))

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        //DATE
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            label = "Date",
            placeholder = "Date",
            text = footBallMatchViewModel.date,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Text,
            onChange = { footBallMatchViewModel.onDateChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //NUMERO MAXIMO
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            label = "Número máximo",
            placeholder = "Número máximo",
            text = footBallMatchViewModel.numberMax,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Text,
            onChange = { footBallMatchViewModel.onNumberMaxChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //NUMERO JUGADORES
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            label = "E-Mail",
            placeholder = "E-Mail",
            text = footBallMatchViewModel.numberPlayers,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Email,
            onChange = { footBallMatchViewModel.onNumberPlayersChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        //STREET
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            label = "Dorsal",
            placeholder = "Dorsal",
            text = footBallMatchViewModel.street,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number,
            onChange = { footBallMatchViewModel.onStreetChange(it) }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        val isButtonEnabled: Boolean by footBallMatchViewModel.isButtonEnabled.observeAsState(initial = false)

        Button(
            onClick = {
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Crear partido")
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
