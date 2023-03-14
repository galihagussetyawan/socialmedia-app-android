package com.example.socialmediaappandroid.ui.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.socialmediaappandroid.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var _googleSignInClient: GoogleSignInClient
    private val _currentUser = MutableLiveData<FirebaseUser?>()

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(application.resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        _googleSignInClient = GoogleSignIn.getClient(application, gso)
    }

    fun getCurrentUser(): LiveData<FirebaseUser?> {
        _currentUser.postValue(_auth.currentUser)

        return _currentUser
    }

    fun getIntentSignWithGoogle(): Intent {
        return _googleSignInClient.signInIntent
    }

    fun signWithCredential(account: GoogleSignInAccount): Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        return _auth.signInWithCredential(credential)
    }

    fun signOut() {
        _googleSignInClient.signOut()
        _auth.signOut()
        _currentUser.postValue(null)
    }
}