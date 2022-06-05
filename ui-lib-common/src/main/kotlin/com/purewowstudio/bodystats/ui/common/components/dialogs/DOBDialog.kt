package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.components.DatePicker
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import java.time.LocalDate

@Composable
fun DOBDialog(
    onConfirmClicked: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        DOBDialogContent(
            onConfirmClicked = onConfirmClicked,
            onDismiss = onDismiss
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DOBDialogContent(
    initialDate: LocalDate = LocalDate.now().minusYears(18),
    onConfirmClicked: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {

    val selectedDate = remember { mutableStateOf(initialDate) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            DatePicker(
                initialDate = initialDate,
                onDateSelected = { selectedDate.value = it })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.cancel))
                }
                TextButton(onClick = {
                    onConfirmClicked.invoke(selectedDate.value)
                }) {
                    Text(text = stringResource(id = R.string.set))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSomeDialogContent() {
    BodyStatsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            DOBDialogContent(
                onConfirmClicked = { /* NO OP */ },
                onDismiss = { /* NO OP */ },
            )
        }
    }
}