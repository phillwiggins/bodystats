package com.purewowstudio.bodystats.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "profile",
            tint = Color.Blue,
            modifier = Modifier.size(150.dp)
                .align(Alignment.Center)
        )
    }
}