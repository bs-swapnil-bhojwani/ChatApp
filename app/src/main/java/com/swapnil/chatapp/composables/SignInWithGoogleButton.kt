package com.swapnil.chatapp.composables

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.swapnil.chatapp.R

@Composable
fun SignInWithGoogleButton(
    modifier: Modifier,
    onSuccess: (FirebaseUser) -> Unit,
    onFailure: (Exception?) -> Unit
) {
    val signInLauncher = rememberLauncherForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result: FirebaseAuthUIAuthenticationResult ->
        val response = result.idpResponse
        if (result.resultCode == Activity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
            user?.let {
                onSuccess(it)
            } ?: onFailure(Exception("Unable to get the user!"))
        } else {
            if (response != null) {
                onFailure(response.error)
            }
        }
    }

    val providers = remember {
        arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
    }

    val signInIntent = remember {
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    ElevatedButton(onClick = { signInLauncher.launch(signInIntent) }) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(end = 12.dp),
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Google icon"
        )
        Text("Sign In with Google", fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview
fun SignInButtonPreview(modifier: Modifier = Modifier) {
    SignInWithGoogleButton(modifier = modifier, onSuccess = {

    }, onFailure = {

    })
}
