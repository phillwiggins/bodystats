package com.purewowstudio.bodystats.ui.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.R

@Composable
fun Avatar() {
    Image(
        painter = painterResource(R.drawable.avatar_male),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(CircleShape)
    )
}