package com.purewowstudio.bodystats.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.purewowstudio.bodystats.domain.base.toDateOfBirth
import com.purewowstudio.bodystats.domain.entities.Gender
import com.purewowstudio.bodystats.domain.entities.User
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
    val viewModel: ProfileScreenViewModel = hiltViewModel()
    ProfileScreenContent(
        uiState = viewModel.uiState,
        onDOBUpdated = viewModel::onDOBUpdated,
        onGenderUpdated = viewModel::onGenderUpdated,
        onDismissDOBDialogCalled = viewModel::onDismissDOBDialogCalled,
        onDismissGenderDialogCalled = viewModel::onDismissGenderDialogCalled,
        onDismissHeightDialogCalled = viewModel::onDismissHeightDialogCalled,
        onDismissWeightDialogCalled = viewModel::onDismissWeightDialogCalled,
        onDOBDialogClicked = viewModel::onDOBDialogClicked,
        onGenderDialogClicked = viewModel::onGenderDialogClicked,
        onHeightDialogClicked = viewModel::onHeightDialogClicked,
        onWeightDialogClicked = viewModel::onWeightDialogClicked
    )
}

@Composable
fun ProfileScreenContent(
    uiState: ProfileScreenView,
    onDOBUpdated: (LocalDate) -> Unit,
    onGenderUpdated: (Gender) -> Unit,
    onDOBDialogClicked: () -> Unit,
    onGenderDialogClicked: () -> Unit,
    onWeightDialogClicked: () -> Unit,
    onHeightDialogClicked: () -> Unit,
    onDismissDOBDialogCalled: () -> Unit,
    onDismissGenderDialogCalled: () -> Unit,
    onDismissWeightDialogCalled: () -> Unit,
    onDismissHeightDialogCalled: () -> Unit,
) {
    when (uiState) {
        ProfileScreenView.Loading -> ProfileScreenLoadingState()
        is ProfileScreenView.Loaded -> ProfileScreenLoadedState(
            uiState = uiState,
            onDOBUpdated = onDOBUpdated,
            onGenderUpdated = onGenderUpdated,
            onWeightDialogClicked = onWeightDialogClicked,
            onHeightDialogClicked = onHeightDialogClicked,
            onGenderDialogClicked = onGenderDialogClicked,
            onDOBDialogClicked = onDOBDialogClicked,
            onDismissWeightDialogCalled = onDismissWeightDialogCalled,
            onDismissHeightDialogCalled = onDismissHeightDialogCalled,
            onDismissGenderDialogCalled = onDismissGenderDialogCalled,
            onDismissDOBDialogCalled = onDismissDOBDialogCalled
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
    onGenderUpdated: (Gender) -> Unit,
    onDOBDialogClicked: () -> Unit,
    onGenderDialogClicked: () -> Unit,
    onWeightDialogClicked: () -> Unit,
    onHeightDialogClicked: () -> Unit,
    onDismissDOBDialogCalled: () -> Unit,
    onDismissGenderDialogCalled: () -> Unit,
    onDismissWeightDialogCalled: () -> Unit,
    onDismissHeightDialogCalled: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        if (uiState.isDOBDialogOpen) {
            DOBDialog(
                onConfirmClicked = {
                    onDOBUpdated(it)
                },
                onDismiss = {
                    onDismissDOBDialogCalled()
                }
            )
        }

        if (uiState.isGenderDialogOpen) {
            GenderDialog(
                currentSelection = uiState.user.gender,
                onConfirmClicked = {
                    onGenderUpdated(it)
                },
                onDismiss = {
                    onDismissGenderDialogCalled()
                }
            )
        }

        if (uiState.isWeightDialogOpen) {
            WeightDialog(
                onConfirmClicked = {
                    onDismissWeightDialogCalled()
                },
                onDismiss = {
                    onDismissWeightDialogCalled()
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
                            onClick = onGenderDialogClicked
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = uiState.user.dob.toDateOfBirth(),
                            label = stringResource(id = R.string.birthday),
                            onClick = onDOBDialogClicked
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
                            onClick = onWeightDialogClicked
                        )
                        Spacer(modifier = modifier.width(8.dp))
                        NonEditableTextField(
                            modifier = Modifier.weight(0.5F, false),
                            text = "187cm",
                            label = stringResource(id = R.string.height),
                            onClick = onHeightDialogClicked
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
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
                ),
                isDOBDialogOpen = false,
                isGenderDialogOpen = false,
                isHeightDialogOpen = false,
                isWeightDialogOpen = false,
            ),
            onDOBUpdated = {},
            onGenderUpdated = {},
            onDismissDOBDialogCalled = {},
            onDismissGenderDialogCalled = {},
            onDismissHeightDialogCalled = {},
            onDismissWeightDialogCalled = {},
            onDOBDialogClicked = {},
            onGenderDialogClicked = {},
            onHeightDialogClicked = {},
            onWeightDialogClicked = {}
        )
    }
}