package com.example.cityfootie_compose.ui.components.date_time_picker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cityfootie_compose.ui.screens.create_football_match.CreateFootballMatchViewModel
import java.util.Calendar
import java.util.Date

@Composable
fun DateTimePicker(
    context: Context,
    createFootBallMatchViewModel: CreateFootballMatchViewModel = hiltViewModel(),
) {
    /**
     * Datos del día, mes y año.
     */
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

    /**
     * Datos de la Hora.
     */
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