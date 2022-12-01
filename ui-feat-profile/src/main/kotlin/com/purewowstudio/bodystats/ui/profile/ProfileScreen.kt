package com.purewowstudio.bodystats.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.domain.base.getDateTimeAtEndOfDay
import com.purewowstudio.bodystats.domain.base.toDateOfBirth
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.domain.entities.User
import com.purewowstudio.bodystats.domain.entities.Weight
import com.purewowstudio.bodystats.ui.common.components.Avatar
import com.purewowstudio.bodystats.ui.common.components.NonEditableTextField
import com.purewowstudio.bodystats.ui.common.components.dialogs.DOBDialog
import com.purewowstudio.bodystats.ui.common.components.dialogs.GenderDialog
import com.purewowstudio.bodystats.ui.common.components.dialogs.WeightDialog
import com.purewowstudio.bodystats.ui.common.theme.BodyStatsTheme
import java.time.LocalDate
import java.time.Month

@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileScreenViewModel>()
    ProfileScreenContent(
        uiState = viewModel.uiState,
        onDOBUpdated = viewModel::onDOBUpdated,
        onGenderUpdated = viewModel::onGenderUpdated
    )
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileScreenView,
    onDOBUpdated: (LocalDate) -> Unit,
    onGenderUpdated: (Gender) -> Unit
) {
    when (uiState) {
        ProfileScreenView.Loading -> ProfileScreenLoadingState()
        is ProfileScreenView.Loaded -> ProfileScreenLoadedState(
            uiState = uiState,
            onDOBUpdated = onDOBUpdated,
            onGenderUpdated = onGenderUpdated
        )
    }
}

@Composable
fun ProfileScreenLoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ProfileScreenLoadedState(
    modifier: Modifier = Modifier,
    uiState: ProfileScreenView.Loaded,
    onDOBUpdated: (LocalDate) -> Unit,
    onGenderUpdated: (Gender) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val openDobDialog = remember { mutableStateOf(false) }
        val openGenderDialog = remember { mutableStateOf(false) }
        val openWeightDialog = remember { mutableStateOf(false) }

        if (openDobDialog.value) {
            DOBDialog(
                onConfirmClicked = {
                    onDOBUpdated(it)
                    openDobDialog.value = false
                },
                onDismiss = {
                    openDobDialog.value = false
                }
            )
        }

        if (openGenderDialog.value) {
            GenderDialog(
                currentSelection = uiState.user.gender,
                onConfirmClicked = {
                    onGenderUpdated(it)
                    openGenderDialog.value = false
                },
                onDismiss = {
                    openGenderDialog.value = false
                }
            )
        }

        if (openWeightDialog.value) {
            WeightDialog(
                initialWeight = Weight.kilograms(96.45), //TODO,
                onConfirmClicked = {
                    //TODO
                    openWeightDialog.value = false
                },
                onDismiss = {
                    openWeightDialog.value = false
                }
            )
        }
        Avatar()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = uiState.user.name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = uiState.user.description,
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
                            text = stringResource(id = uiState.user.gender.name),
                            label = stringResource(id = R.string.gender),
                            onClick = { openGenderDialog.value = true }
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = uiState.user.dob.toDateOfBirth(),
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
                            onClick = { openWeightDialog.value = true }
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
        ProfileScreenLoadedState(
            uiState = ProfileScreenView.Loaded(
                user = User(
                    name = "Phill Wiggins",
                    gender = Gender.Male,
                    description = "Software Engineer",
                    avatarUrl = "",
                    dob = LocalDate.of(1988, Month.MAY, 15)
                )
            ),
            onDOBUpdated = {},
            onGenderUpdated = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreviewDark() {
    BodyStatsTheme {
        ProfileScreenLoadedState(
            uiState = ProfileScreenView.Loaded(
                user = User(
                    name = "Phill Wiggins",
                    gender = Gender.Male,
                    description = "Software Engineer",
                    avatarUrl = "",
                    dob = LocalDate.of(1988, Month.MAY, 15)
                )
            ),
            onDOBUpdated = {},
            onGenderUpdated = {}
        )
    }
}