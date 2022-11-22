package com.purewowstudio.bodystats.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
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
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.ui.common.components.Avatar
import com.purewowstudio.bodystats.ui.common.components.NonEditableTextField
import com.purewowstudio.bodystats.ui.common.components.dialogs.DOBDialog
import com.purewowstudio.bodystats.ui.common.components.dialogs.GenderDialog
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme

@Composable
fun ProfileScreen() {
    ProfileScreenContent()
}

@Composable
fun ProfileScreenContent(
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val openDobDialog = remember { mutableStateOf(false) }
        val openGenderDialog = remember { mutableStateOf(false) }
        val dateOfBirth = remember { mutableStateOf("15 May 1988") }
        val gender = remember { mutableStateOf<Gender>(Gender.Male) }

        if (openDobDialog.value) {
            DOBDialog(
                onConfirmClicked = {
                    dateOfBirth.value = it.toString()
                    openDobDialog.value = false
                },
                onDismiss = {
                    openDobDialog.value = false
                }
            )
        }

        if (openGenderDialog.value) {
            GenderDialog(
                currentSelection = gender.value,
                onConfirmClicked = {
                    gender.value = it
                    openGenderDialog.value = false
                },
                onDismiss = {
                    openGenderDialog.value = false
                }
            )
        }
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
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6F)
        )
        Spacer(modifier = modifier.height(16.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "ABOUT YOU",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = modifier.height(8.dp))
            ElevatedCard {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = stringResource(id = gender.value.name),
                            label = stringResource(id = R.string.gender),
                            onClick = { openGenderDialog.value = true }
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = dateOfBirth.value,
                            label = stringResource(id = R.string.birthday),
                            onClick = { openDobDialog.value = true }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                    ) {
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = "188lbs",
                            label = stringResource(id = R.string.weight),
                            onClick = { /* NO OP */ }
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = "187cm",
                            label = stringResource(id = R.string.height),
                            onClick = { /* NO OP */ }
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreviewLight() {
    BodyStatsTheme {
        ProfileScreen()
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark() {
    BodyStatsTheme {
        ProfileScreen()
    }
}