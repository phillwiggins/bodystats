package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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

    val radioOptions = listOf(
        stringResource(id = R.string.male),
        stringResource(id = R.string.female)
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Material3Dialog(
            icon = Icons.Outlined.Female,
            iconContentDescription = "Gender",
            title = stringResource(id = R.string.gender),
            subtitle = stringResource(id = R.string.gender_desc),
            content = {
                GenderDialogContent(
                    radioOptions = radioOptions,
                    onOptionSelected = onOptionSelected,
                    selectedOption = selectedOption
                )
            },
            onConfirmClicked = onConfirmClicked,
            onDismiss = onDismiss
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDialogContent(
    radioOptions: List<String>,
    onOptionSelected: (String) -> Unit,
    selectedOption: String
) {

    Column {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
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
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Preview
@Composable
fun PreviewGenderDialogContent() {
    BodyStatsTheme {
        GenderDialog(
            onConfirmClicked = { /* NO OP*/ },
            onDismiss = { /* NO OP*/ }
        )
    }
}