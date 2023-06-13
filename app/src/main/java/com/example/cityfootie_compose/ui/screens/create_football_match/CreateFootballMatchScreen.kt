package com.example.cityfootie_compose.ui.screens.create_football_match

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.components.data_field.DataField
import com.example.cityfootie_compose.ui.components.date_time_picker.DateTimePicker
import com.example.cityfootie_compose.util.toFloat
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateFootballMatchScreen(
    latitude: String,
    longitude: String,
    goBack: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    goBack()
                },
            )
        }
    }) {
        BodyContent(latitude, longitude, goBack)
    }
}

@Composable
fun BodyContent(
    latitude: String,
    longitude: String,
    goBack: () -> Unit,
    createFootBallMatchViewModel: CreateFootballMatchViewModel = hiltViewModel(),
) {
    val isLoading: Boolean = createFootBallMatchViewModel.isLoading

    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val isError: Boolean = createFootBallMatchViewModel.isError
    val isButtonEnabled: Boolean by createFootBallMatchViewModel.isButtonEnabled.observeAsState(initial = false)


    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
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

        //DATE
        DateTimePicker(context)

        Spacer(modifier = Modifier.padding(0.dp))

        //NUMERO MAXIMO
        DataField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(top = 0.dp)
                .focusRequester(focusRequester),
            label = "Número máximo de jugadores",
            placeholder = "Número máximo de jugadores",
            text = createFootBallMatchViewModel.numberMax,
            imeAction = ImeAction.Next,
            isEnabled = true,
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardType = KeyboardType.Number,
            onChange = { createFootBallMatchViewModel.onNumberMaxChange(it) }
        )

        Spacer(modifier = Modifier.padding(0.dp))

        Text(
            text = "No puedes crear un partido en el pasado",
            modifier = Modifier.alpha(isError.toFloat()),
            color = MaterialTheme.colors.error
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                createFootBallMatchViewModel.postFootballMatch(latitude, longitude)
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Crear partido")
        }

        val response = createFootBallMatchViewModel.response
        val isCompleted = createFootBallMatchViewModel.isCompleted
        LaunchedEffect(response) {
            if (response != null) {
                if (isCompleted) {
                    goBack()
                }
            }
        }
    }
}