package com.swapnil.chatapp.feature.editProfile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swapnil.chatapp.composables.ChatAppBar
import com.swapnil.chatapp.composables.TextWithAsterisk
import com.swapnil.chatapp.ui.theme.Primary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditProfileScreen(email: String) {
    var name by remember {
        mutableStateOf("")
    }
    var bio by remember {
        mutableStateOf("")
    }
    var nameError by remember {
        mutableStateOf(false)
    }
    var bioError by remember {
        mutableStateOf(false)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


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
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Person",
                        tint = Primary
                    )
                },
                value = name,
                onValueChange = {
                    nameError = it.isBlank()
                    name = it
                },
                isError = nameError,
                supportingText = if (nameError) {
                    { Text(text = "Required!") }
                } else {
                    null
                },
                label = { TextWithAsterisk(text = "Name") })

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

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Bio",
                        tint = Primary
                    )
                },
                value = bio,
                onValueChange = {
                    bioError = it.isBlank()
                    bio = it
                },
                label = {
                    TextWithAsterisk(text = "Bio")
                },
                isError = bioError,
                supportingText = if (bioError) {
                    { Text(text = "Required!") }
                } else {
                    null
                },
            )

            Button(modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally), onClick = {
                if (name.isBlank() && bio.isBlank()) {
                    nameError = true
                    bioError = true
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Please input your name and bio")
                    }
                    return@Button
                }
                if (name.isBlank()) {
                    nameError = true
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Please enter your name")
                    }
                    return@Button
                }
                if (bio.isBlank()) {
                    bioError = true
                    scope.launch {
                        snackbarHostState.showSnackbar(message = "Please enter your bio")
                    }
                    return@Button
                }
                scope.launch {
                    snackbarHostState.showSnackbar(message = "Your name is $name,bio is $bio")
                }
            }) {
                Text(text = "Save")
            }
        }
    }
}