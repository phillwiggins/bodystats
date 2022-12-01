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
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun WeightDialog(
    initialWeight: Weight,
    onConfirmClicked: (Weight) -> Unit,
    onDismiss: () -> Unit
) {

    val selectedDate = remember { mutableStateOf(initialWeight) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Material3Dialog(
            icon = Icons.Outlined.Cake,
            iconContentDescription = "Weight",
            title = stringResource(id = R.string.weight),
            subtitle = stringResource(id = R.string.weight_desc),
            content = {
                // TODO
            },
            onConfirmClicked = { onConfirmClicked.invoke(selectedDate.value) },
            onDismiss = onDismiss
        )
    }
}

@Preview
@Composable
fun PreviewWeightContent() {
    BodyStatsTheme {
        WeightDialog(
            initialWeight = Weight.kilograms(96.01),
            onConfirmClicked = { /* NO OP*/ },
            onDismiss = { /* NO OP */ }
        )
    }
}