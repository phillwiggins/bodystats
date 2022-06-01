package com.purewowstudio.bodystats.ui.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.purewowstudio.bodystats.ui.common.components.BSTopAppBar
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun OverviewScreen() {
    Column {
        BSTopAppBar(title = "Overview")
    }
}

@Preview
@Composable
fun OverviewScreenPreview() {
    BodyStatsTheme {
        OverviewScreen()
    }
}