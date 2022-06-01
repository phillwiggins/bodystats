package com.purewowstudio.bodystats.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.components.Avatar
import com.purewowstudio.bodystats.ui.common.components.BSTopAppBar
import com.purewowstudio.bodystats.ui.common.components.NonEditableTextField
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val configuration = LocalConfiguration.current

        BSTopAppBar(
            title = "Profile",
            addMoreMenu = true,
            onMoreMenuClicked = { /* TODO */ }
        )
        Avatar()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Phill Wiggins",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Something Something",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4F)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = "Male",
                label = stringResource(id = R.string.gender)
            )
            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = "15 May 1988",
                label = stringResource(id = R.string.birthday)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = "188lbs",
                label = stringResource(id = R.string.weight)
            )
            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = "187cm",
                label = stringResource(id = R.string.height)
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    BodyStatsTheme {
        ProfileScreen()
    }
}