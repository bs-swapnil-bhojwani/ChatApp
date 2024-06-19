package com.swapnil.chatapp.feature.editProfile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.streamliners.compose.comp.select.RadioGroup
import com.streamliners.compose.comp.textInput.config.InputConfig
import com.streamliners.compose.comp.textInput.config.text
import com.streamliners.compose.comp.textInput.state.TextInputState
import com.streamliners.compose.comp.textInput.state.allHaveValidInputs
import com.streamliners.compose.comp.textInput.state.value
import com.swapnil.chatapp.composables.ChatAppBar
import com.swapnil.chatapp.composables.TextInputLayout
import com.swapnil.chatapp.domain.model.Gender
import com.swapnil.chatapp.domain.model.User
import com.swapnil.chatapp.ui.theme.Primary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun EditProfileScreen(email: String, viewModel: EditProfileViewModel) {
    val nameInput = remember {
        mutableStateOf(
            TextInputState(label = "Name", inputConfig = InputConfig.text {
                minLength = 3
                maxLength = 25
            })
        )
    }

    val bioInput = remember {
        mutableStateOf(
            TextInputState(label = "Bio", inputConfig = InputConfig.text {
                minLength = 10
                maxLength = 50
            })
        )
    }
    val emailInput = remember {
        mutableStateOf(
            TextInputState(label = "Email", value = email)
        )
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val gender = remember {
        mutableStateOf<Gender?>(null)
    }
    var genderError by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = gender.value) {
        if (gender.value !== null) {
            genderError = false
        }
    }

    Scaffold(
        topBar = {
            ChatAppBar(title = "Profile")
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.imePadding()
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextInputLayout(state = nameInput, leadingIcon = Icons.Filled.Person)

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Person",
                        tint = Primary
                    )
                },
                value = email,
                readOnly = true,
                onValueChange = { },
                label = {
                    Text(text = "Email")
                })

            TextInputLayout(state = bioInput, leadingIcon = Icons.Filled.Info)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .selectableGroup(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                )
            ) {
                RadioGroup(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    state = gender,
                    title = "Gender",
                    options = Gender.entries.toList(),
                    labelExtractor = { it.name }
                )
                if (genderError) {
                    Text(
                        text = "Required!",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Button(modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally), onClick = {
                if (TextInputState.allHaveValidInputs(
                        nameInput,
                        bioInput
                    )
                ) {
                    gender.value?.let {
                        val user = User(
                            name = nameInput.value(),
                            email = email,
                            gender = it,
                            bio = bioInput.value()
                        )
                        viewModel.saveUser(user) {
                            scope.launch {
                                snackbarHostState.showSnackbar(message = "Registration successful!")
                            }
                        }
                    }
                    return@Button
                }
                if (gender.value === null) {
                    genderError = true
                }
            }) {
                Text(text = "Save")
            }
        }
    }
}