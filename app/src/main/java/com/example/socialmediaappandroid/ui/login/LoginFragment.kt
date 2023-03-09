package com.example.socialmediaappandroid.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.socialmediaappandroid.R
import com.example.socialmediaappandroid.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {
    private val REQ_CODE: Int = 123
    private lateinit var _binding: FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelFactory =
            AuthViewModelFactory(
                requireActivity().application,
                getString(R.string.default_web_client_id)
            )

        authViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSignButton()
        initLogoutButton()
    }

    private fun initSignButton() {

        _binding.btnSigninGoogle.setOnClickListener {
            val signIntent: Intent = authViewModel.googleSignInClient.signInIntent
            launcher.launch(signIntent)
        }
    }

    private fun initLogoutButton() {
        _binding.btnLogout.setOnClickListener {
            authViewModel.googleSignInClient.signOut()
        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)

                if (task.isSuccessful) {
                    handleResult(task)
                } else {
                    Toast.makeText(requireActivity(), "FAILDed", Toast.LENGTH_SHORT)
                }
            }
        }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(requireActivity(), e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        authViewModel.auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("LOGIN-FRAGMENT", task.result.user?.displayName.toString())
            }
        }
    }
}
