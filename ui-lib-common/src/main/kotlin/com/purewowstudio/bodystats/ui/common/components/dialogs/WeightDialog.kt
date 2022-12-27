package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.components.ListItemPicker
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun WeightDialog(
    onConfirmClicked: (Weight) -> Unit,
    onDismiss: () -> Unit
) {
    val viewModel = hiltViewModel<WeightDialogViewModel>()
    WeightDialogContent(
        uiState = viewModel.uiState,
        onWeightTypeChanged = viewModel::onWeightTypeChanged,
        onSelectedWholeNumberChanged = viewModel::onSelectedWholeNumberChanged,
        onSelectedDecimalNumberChanged = viewModel::onSelectedDecimalNumberChanged,
        onConfirmClicked = onConfirmClicked,
        onDismiss = onDismiss
    )
}

@Composable
private fun WeightDialogContent(
    uiState: WeightDialogViewUiState,
    onWeightTypeChanged: (Weight.Type) -> Unit,
    onSelectedWholeNumberChanged: (Int) -> Unit,
    onSelectedDecimalNumberChanged: (Int) -> Unit,
    onConfirmClicked: (Weight) -> Unit,
    onDismiss: () -> Unit
) {
    if (uiState.isLoading) {
        CircularProgressIndicator()
    } else {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties()
        ) {
            Material3Dialog(
                icon = Icons.Outlined.MonitorWeight,
                iconContentDescription = "Weight",
                title = stringResource(id = R.string.weight),
                subtitle = stringResource(id = R.string.weight_desc),
                content = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ListItemPicker(
                            value = uiState.selectedWholeNumber,
                            onValueChange = onSelectedWholeNumberChanged,
                            list = uiState.wholeNumbers,
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = ".", color = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(2.dp))
                        ListItemPicker(
                            value = uiState.selectedDecimal,
                            onValueChange = onSelectedDecimalNumberChanged,
                            list = uiState.decimals
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        WeightTypeSelector(
                            weightType = uiState.weightType,
                            onWeightTypeChanged = onWeightTypeChanged
                        )
                    }
                },
                onConfirmClicked = { onConfirmClicked.invoke(Weight.kilograms(uiState.selectedWholeNumber.toDouble() + uiState.selectedDecimal)) },
                onDismiss = onDismiss
            )
        }
    }
}

@Composable
private fun WeightTypeSelector(
    weightType: Weight.Type,
    onWeightTypeChanged: (Weight.Type) -> Unit,
) {
    val kgs = stringResource(id = R.string.kgs)
    val lbs = stringResource(id = R.string.lbs)

    ListItemPicker(
        value = when (weightType) {
            Weight.Type.KILOGRAMS -> kgs
            Weight.Type.POUNDS -> lbs
        },
        onValueChange = {
            val type = when (it) {
                kgs -> Weight.Type.KILOGRAMS
                lbs -> Weight.Type.POUNDS
                else -> Weight.Type.KILOGRAMS
            }
            onWeightTypeChanged(type)
        },
        list = listOf(kgs, lbs)
    )
}

@Preview
@Composable
fun PreviewWeightContent() {
    BodyStatsTheme {
        WeightDialogContent(
            uiState = WeightDialogViewUiState(
                wholeNumbers = listOf(1, 2, 3),
                decimals = listOf(1, 2, 3),
                weightType = Weight.Type.POUNDS,
                selectedWholeNumber = 1,
                selectedDecimal = 1
            ),
            onConfirmClicked = {},
            onDismiss = {},
            onWeightTypeChanged = {},
            onSelectedDecimalNumberChanged = {},
            onSelectedWholeNumberChanged = {}
        )
    }
}