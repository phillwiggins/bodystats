package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun NonEditableTextField(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    if (isPressed) {
        onClick.invoke()
    }

    OutlinedTextField(
        modifier = modifier,
        interactionSource = interactionSource,
        readOnly = true,
        value = text,
        onValueChange = { /* NO OP */ },
        label = { Text(label) },
    )
}