package com.purewowstudio.bodystats.ui.weight

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun WeightScreen() {
    val viewModel = hiltViewModel<WeightScreenViewModel>()
    WeightScreenContent(
        uiState = viewModel.uiState,
    )

    BackHandler(enabled = true, onBack = viewModel::onBackPressed)
}

@Composable
fun WeightScreenContent(
    uiState: WeightScreenView,
) {
    when (uiState) {
        WeightScreenView.Error -> WeightScreenErrorView()
        WeightScreenView.Loading -> WeightScreenLoadingView()
        is WeightScreenView.Loaded -> WeightScreenLoadedView(uiState = uiState)
    }
}

@Composable
fun WeightScreenErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ERROR")
    }
}

@Composable
fun WeightScreenLoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun WeightScreenLoadedView(
    modifier: Modifier = Modifier,
    uiState: WeightScreenView.Loaded
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            "Durations",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreviewLight() {
    BodyStatsTheme {
        WeightScreenLoadedView(
            uiState = WeightScreenView.Loaded(""),
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark() {
    BodyStatsTheme {
        WeightScreenLoadedView(
            uiState = WeightScreenView.Loaded(""),
        )
    }
}