package com.purewowstudio.bodystats.ui.common.components.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Material3Dialog(
    icon: ImageVector,
    iconContentDescription: String,
    title: String,
    subtitle: String,
    content: @Composable() () -> Unit,
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                modifier = Modifier.height(32.dp),
                imageVector = icon,
                contentDescription = iconContentDescription,
                tint = MaterialTheme.colorScheme.secondary
            )
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                text = title
            )
            Text(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                text = subtitle
            )
            content()
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
                TextButton(onClick = onConfirmClicked) {
                    Text(
                        style = MaterialTheme.typography.labelLarge,
                        text = stringResource(id = R.string.set)
                    )
                }
            }
        }
    }
}