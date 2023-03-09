package com.example.socialmediaappandroid.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application: Application, clientId: String) : AndroidViewModel(application) {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(application, gso)
    }
}