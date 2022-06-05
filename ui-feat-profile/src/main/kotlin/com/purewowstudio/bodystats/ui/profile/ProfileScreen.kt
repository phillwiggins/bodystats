package com.purewowstudio.bodystats.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.purewowstudio.bodystats.ui.common.components.Avatar
import com.purewowstudio.bodystats.ui.common.components.BSTopAppBar
import com.purewowstudio.bodystats.ui.common.components.NonEditableTextField
import com.purewowstudio.bodystats.ui.common.components.dialogs.DOBDialog
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun ProfileScreen() {
    ProfileScreenContent()
}

@Composable
fun ProfileScreenContent(

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val configuration = LocalConfiguration.current
        val openDialog = remember { mutableStateOf(false) }
        val dateOfBirth = remember { mutableStateOf("15 May 1988") }

        if (openDialog.value) {
            DOBDialog(
                onConfirmClicked = {
                    dateOfBirth.value = it.toString()
                    openDialog.value = false
                },
                onDismiss = {
                    openDialog.value = false
                }
            )
        }

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
                label = stringResource(id = R.string.gender),
                onClick = { /* NO OP */ }
            )
            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = dateOfBirth.value,
                label = stringResource(id = R.string.birthday),
                onClick = { openDialog.value = true }
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
                label = stringResource(id = R.string.weight),
                onClick = { /* NO OP */ }
            )
            NonEditableTextField(
                modifier = Modifier.width((configuration.screenWidthDp.toFloat() * 0.44F).dp),
                text = "187cm",
                label = stringResource(id = R.string.height),
                onClick = { /* NO OP */ }
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