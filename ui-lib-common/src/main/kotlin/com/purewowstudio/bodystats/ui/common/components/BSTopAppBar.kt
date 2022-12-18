package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BSTopAppBar(
    title: String,
    isNavigationIconDisplayed: Boolean,
    addMoreMenu: Boolean = false,
    onMoreMenuClicked: () -> Unit = { /* NO OP */ },
    onBackButtonClicked: () -> Unit,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (isNavigationIconDisplayed) {
                IconButton(onClick = onBackButtonClicked) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
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