package com.example.socialmediaappandroid.ui.login

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel(application: Application, clientId: String) : AndroidViewModel(application) {

    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var _googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId)
            .requestEmail()
            .build()

        _googleSignInClient = GoogleSignIn.getClient(application, gso)
    }

    fun getCurrentUser(): FirebaseUser? {
        return _auth.currentUser
    }

    fun getIntentSignWithGoogle(): Intent {
        return _googleSignInClient.signInIntent
    }

    fun signWithCredential(account: GoogleSignInAccount): Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        return _auth.signInWithCredential(credential)
    }

    fun signOut() {
        _auth.signOut()
    }
}