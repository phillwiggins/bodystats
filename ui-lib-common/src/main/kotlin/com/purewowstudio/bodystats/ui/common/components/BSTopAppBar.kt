package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BSTopAppBar(
    title: String,
    addMoreMenu: Boolean = false,
    onMoreMenuClicked: () -> Unit = { /* NO OP */},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            if (addMoreMenu) {
                IconButton(onClick = onMoreMenuClicked) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    )
}