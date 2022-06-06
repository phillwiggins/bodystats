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
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.domain.entities.Gender.Female
import com.purewowstudio.bodystats.domain.entities.Gender.Male
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun GenderDialog(
    currentSelection: Gender,
    onConfirmClicked: (Gender) -> Unit,
    onDismiss: () -> Unit
) {
    val genders = listOf(Male, Female)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(currentSelection) }

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
                    radioOptions = genders,
                    onOptionSelected = onOptionSelected,
                    selectedOption = selectedOption
                )
            },
            onConfirmClicked = { onConfirmClicked.invoke(selectedOption) },
            onDismiss = onDismiss
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDialogContent(
    radioOptions: List<Gender>,
    onOptionSelected: (Gender) -> Unit,
    selectedOption: Gender
) {

    Column {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
        radioOptions.forEach { gender ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (gender == selectedOption),
                        onClick = { onOptionSelected(gender) }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (gender == selectedOption),
                    onClick = { onOptionSelected(gender) }
                )
                Text(
                    text = stringResource(id = gender.name),
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
            currentSelection = Male,
            onConfirmClicked = { /* NO OP*/ },
            onDismiss = { /* NO OP*/ }
        )
    }
}