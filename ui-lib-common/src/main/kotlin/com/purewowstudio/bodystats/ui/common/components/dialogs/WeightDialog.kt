package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Scale
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.ui.common.R
import com.purewowstudio.bodystats.ui.common.components.ListItemPicker
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun WeightDialog(
    initialWeight: Weight,
    onConfirmClicked: (Weight) -> Unit,
    onDismiss: () -> Unit
) {

    val weightTypes = listOf("Kg", "Lbs")
    val lbsList = getLbsList()
    val kgsList = getKgsList()
    val decimalList = getDecimalList()

    var selectedWeight by remember { mutableStateOf(initialWeight.inKilograms.toInt()) }
    var selectedDecimal by remember { mutableStateOf(decimalList.first()) }
    var selectedWeightType by remember { mutableStateOf(weightTypes.first()) }

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
                        value = selectedWeight,
                        onValueChange = { selectedWeight = it },
                        list = if (selectedWeightType == weightTypes[0]) kgsList else lbsList,
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = ".", color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(2.dp))
                    ListItemPicker(
                        value = selectedDecimal,
                        onValueChange = { selectedDecimal = it },
                        list = decimalList
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ListItemPicker(
                        value = selectedWeightType,
                        onValueChange = { selectedWeightType = it },
                        list = weightTypes
                    )
                }
            },
            onConfirmClicked = { onConfirmClicked.invoke(Weight.kilograms(selectedWeight.toDouble() + selectedDecimal)) },
            onDismiss = onDismiss
        )
    }
}

private fun getKgsList(): List<Int> {
    return buildList {
        val bottomWeight = 40
        val topWeight = 200
        val range = topWeight - bottomWeight

        add(bottomWeight)

        repeat(range) {
            add(bottomWeight + it)
        }
    }
}

private fun getLbsList(): List<Int> {
    return buildList {
        val bottomWeight = 80
        val topWeight = 500
        val range = topWeight - bottomWeight

        add(bottomWeight)

        repeat(range) {
            add(bottomWeight + it)
        }
    }
}

private fun getDecimalList(): List<Int> {
    return buildList {
        repeat(10) {
            add(it)
        }
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