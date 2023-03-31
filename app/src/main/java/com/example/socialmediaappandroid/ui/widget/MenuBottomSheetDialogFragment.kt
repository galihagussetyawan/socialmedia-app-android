package com.example.socialmediaappandroid.ui.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.socialmediaappandroid.R
import com.example.socialmediaappandroid.databinding.FragmentMenuBottomSheetDialogBinding
import com.example.socialmediaappandroid.ui.viewmodel.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentMenuBottomSheetDialogBinding
    private lateinit var _authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBottomSheetDialogBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _authViewModel.getCurrentUser().observe(viewLifecycleOwner) {
            if (it == null) {
                _binding.lyAuthenticated.visibility = View.GONE
                _binding.lyNoAuthenticated.visibility = View.VISIBLE

                _binding.btnSignin.setOnClickListener {
                    findNavController().navigate(R.id.loginFragment)
                }

            } else {
                _binding.lyAuthenticated.visibility = View.VISIBLE
                _binding.lyNoAuthenticated.visibility = View.GONE
            }
        }
    }
}