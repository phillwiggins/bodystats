package com.purewowstudio.bodystats.ui.common.components

import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.purewowstudio.bodystats.ui.common.R
import java.time.LocalDate

@Composable
fun DatePicker(
    initialDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit
) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val view = LayoutInflater.from(context)
                .inflate(R.layout.date_picker, null)

            val datePicker = view.findViewById<DatePicker>(R.id.datePicker)

            datePicker.init(
                initialDate.year,
                initialDate.monthValue,
                initialDate.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                onDateSelected(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
            }

            datePicker
        }
    )
}

@Preview
@Composable
fun DatePickerPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        DatePicker(onDateSelected = { /* NO OP*/ })
    }
}