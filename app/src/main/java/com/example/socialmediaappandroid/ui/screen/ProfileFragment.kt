package com.example.socialmediaappandroid.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.socialmediaappandroid.databinding.FragmentProfileBinding
import com.example.socialmediaappandroid.ui.widget.MenuBottomSheetDialogFragment

class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    private fun setupToolbar() {
        _binding.btnBack.setOnClickListener() {
            findNavController().navigateUp()
        }

        _binding.btnMenu.setOnClickListener {
            MenuBottomSheetDialogFragment().show(childFragmentManager, "menu-bottom-sheet")
        }
    }
}