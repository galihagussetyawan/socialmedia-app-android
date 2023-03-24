package com.example.socialmediaappandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.socialmediaappandroid.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var _binding: FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(layoutInflater)
        return _binding.root
    }
}