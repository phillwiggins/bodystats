package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                modifier = Modifier.height(32.dp),
                imageVector = Icons.Outlined.Cake,
                contentDescription = "Birthday",
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(id = R.string.date_of_birth)
            )
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = R.string.date_of_birth_desc)
            )
            DatePicker(
                initialDate = initialDate,
                onDateSelected = { selectedDate.value = it })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(
                        style = MaterialTheme.typography.labelLarge,
                        text = stringResource(id = R.string.cancel)
                    )
                }
                TextButton(onClick = {
                    onConfirmClicked.invoke(selectedDate.value)
                }) {
                    Text(
                        style = MaterialTheme.typography.labelLarge,
                        text = stringResource(id = R.string.set)
                    )
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