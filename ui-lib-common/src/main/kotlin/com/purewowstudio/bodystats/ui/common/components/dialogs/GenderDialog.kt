package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Female
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
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun GenderDialog(
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        GenderDialogContent(
            onConfirmClicked = onConfirmClicked,
            onDismiss = onDismiss
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDialogContent(
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
) {

    val radioOptions = listOf(
        stringResource(id = R.string.male),
        stringResource(id = R.string.female)
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                modifier = Modifier.height(32.dp),
                imageVector = Icons.Outlined.Female,
                contentDescription = "Gender",
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(id = R.string.gender)
            )
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = R.string.gender_desc)
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Column {
                radioOptions.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = { onOptionSelected(text) }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.surfaceVariant
            )
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
                    onConfirmClicked.invoke()
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
fun PreviewGenderDialogContent() {
    BodyStatsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            GenderDialogContent(
                onConfirmClicked = { /* NO OP */ },
                onDismiss = { /* NO OP */ },
            )
        }
    }
}