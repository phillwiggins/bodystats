package com.purewowstudio.bodystats.ui.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
        colors = outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = MaterialTheme.colorScheme.onSurface,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier,
        interactionSource = interactionSource,
        readOnly = true,
        value = text,
        onValueChange = { /* NO OP */ },
        label = { Text(text = label, color = MaterialTheme.colorScheme.onSurface) },
    )
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun NonEditableTextFieldLight() {
    NonEditableTextField(label = "Test", text = "Test input") {
        // NO OP
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NonEditableTextFieldDark() {
    NonEditableTextField(label = "Test", text = "Test input") {
        // NO OP
    }
}