package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.components.DatePicker
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import java.time.LocalDate

@Composable
fun DOBDialog(
    initialDate: LocalDate = LocalDate.now().minusYears(18),
    onConfirmClicked: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {

    val selectedDate = remember { mutableStateOf(initialDate) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Material3Dialog(
            icon = Icons.Outlined.Cake,
            iconContentDescription = "Birthday",
            title = stringResource(id = R.string.date_of_birth),
            subtitle = stringResource(id = R.string.date_of_birth_desc),
            content = {
                DOBDialogContent(
                    onDateSelected = { selectedDate.value = it }
                )
            },
            onConfirmClicked = { onConfirmClicked.invoke(selectedDate.value) },
            onDismiss = onDismiss
        )
    }
}

@Composable
fun DOBDialogContent(
    initialDate: LocalDate = LocalDate.now().minusYears(18),
    onDateSelected: (LocalDate) -> Unit
) {
    DatePicker(
        initialDate = initialDate,
        onDateSelected = onDateSelected
    )
}

@Preview
@Composable
fun PreviewSomeDialogContent() {
    BodyStatsTheme {
        DOBDialog(
            onConfirmClicked = { /* NO OP*/ },
            onDismiss = { /* NO OP */ }
        )
    }
}