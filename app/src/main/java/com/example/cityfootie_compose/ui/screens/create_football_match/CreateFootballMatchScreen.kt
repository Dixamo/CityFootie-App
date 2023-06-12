package com.example.cityfootie_compose.ui.screens.create_football_match

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.screens.register.DataField
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
        TopAppBar() {
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
    var isLoading: Boolean = createFootBallMatchViewModel.isLoading
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

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        //DATE

        val context = LocalContext.current
        DateTimePicker(context)

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

        var isError: Boolean = createFootBallMatchViewModel.isError
        Text(
            text = "No puedes crear un partido en el pasado",
            modifier = Modifier.alpha(isError.toFloat()),
            color = MaterialTheme.colors.error
        )

        Spacer(modifier = Modifier.padding(10.dp))

        val isButtonEnabled: Boolean by createFootBallMatchViewModel.isButtonEnabled.observeAsState(
            initial = false
        )

        Button(
            onClick = {
                createFootBallMatchViewModel.postFootballMatch(latitude, longitude)
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Crear partido")
        }

        var response = createFootBallMatchViewModel.response
        var isCompleted = createFootBallMatchViewModel.isCompleted
        LaunchedEffect(response) {
            if (response != null) {
                if (isCompleted) {
                    goBack()
                }
            }
        }
    }
}

@Composable
fun DateField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    placeholder: String,
    onChange: (String) -> Unit,
    onClick: () -> Unit,
    isEnabled: Boolean = false
) {
    Column(modifier = Modifier.height(90.dp)) {
        OutlinedTextField(
            modifier = Modifier.clickable {
                onClick()
            },
            value = text,
            onValueChange = { onChange(it) },
            textStyle = TextStyle(fontSize = 18.sp),
            enabled = isEnabled,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = Color.Gray,
                disabledBorderColor = Color.Gray,
                disabledTextColor = Color.LightGray
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
fun DateTimePicker(
    context: Context,
    createFootBallMatchViewModel: CreateFootballMatchViewModel = hiltViewModel(),
) {
    //DIA
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value =
                "$year-${createFootBallMatchViewModel.addLeadingZero((month + 1).toString())}-${
                    createFootBallMatchViewModel.addLeadingZero(dayOfMonth.toString())
                }"
        }, year, month, day
    )

    //HORA
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            time.value = "${createFootBallMatchViewModel.addLeadingZero(hour.toString())}:${
                createFootBallMatchViewModel.addLeadingZero(minute.toString())
            }"
        }, hour, minute, false
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        createFootBallMatchViewModel.dateString = "${date.value} ${time.value}"
        DateField(
            onClick = {
                timePickerDialog.show()
                datePickerDialog.show()
            },
            text = createFootBallMatchViewModel.dateString,
            label = "Fecha",
            placeholder = "Fecha",
            onChange = { createFootBallMatchViewModel.onDateChange(it) }
        )
        Log.d("Mes", Calendar.getInstance().get(Calendar.MONTH).toString())
    }
}