package com.example.socialmediaappandroid.ui.screen

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.FragmentProfileBinding
import com.example.socialmediaappandroid.ui.widget.MenuBottomSheetDialogFragment

class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val _tabPosition = MutableLiveData<String?>("posts")

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
        val colorPrimaryContainer = TypedValue().let {
            requireContext().theme.resolveAttribute(
                com.google.android.material.R.attr.colorPrimaryContainer,
                it,
                true
            )
            requireContext().getColor(it.resourceId)
        }

        val colorBackground = TypedValue().let {
            requireContext().theme.resolveAttribute(
                com.google.android.material.R.attr.colorAccent,
                it, true
            )
            requireContext().getColor(it.resourceId)
        }

        _binding.btnBack.setOnClickListener() {
            findNavController().navigateUp()
        }

        _binding.btnMenu.setOnClickListener {
            MenuBottomSheetDialogFragment().show(childFragmentManager, "menu-bottom-sheet")
        }

//        harcoded avatar image
        Glide.with(this)
            .load("https://www.w3schools.com/howto/img_avatar.png")
            .into(_binding.ivProfilePicture)

        _binding.btnTabPosts.setOnClickListener {
            _tabPosition.postValue("posts")

            _binding.btnTabReplis.background.setTint(colorPrimaryContainer)
            _binding.btnTabTags.background.setTint(colorPrimaryContainer)
        }

        _binding.btnTabReplis.setOnClickListener {
            _tabPosition.postValue("replies")

            _binding.btnTabPosts.background.setTint(colorPrimaryContainer)
            _binding.btnTabTags.background.setTint(colorPrimaryContainer)
        }

        _binding.btnTabTags.setOnClickListener {
            _tabPosition.postValue("tags")

            _binding.btnTabReplis.background.setTint(colorPrimaryContainer)
            _binding.btnTabPosts.background.setTint(colorPrimaryContainer)
        }

        _tabPosition.observe(viewLifecycleOwner) {
            _binding.tvTabPos.text = it.toString()

            when (it) {
                "posts" -> _binding.btnTabPosts.background.setTint(colorBackground)
                "replies" -> _binding.btnTabReplis.background.setTint(colorBackground)
                "tags" -> _binding.btnTabTags.background.setTint(colorBackground)
            }
        }
    }
}