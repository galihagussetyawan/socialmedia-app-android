package com.example.socialmediaappandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.socialmediaappandroid.data.network.FeedRepository
import com.example.socialmediaappandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var _homeAdapter: HomeAdapter
    private val feedRepository = FeedRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
    }

    private fun setupAdapter() {
        _homeAdapter = HomeAdapter()
        feedRepository.getAllFeeds().observe(viewLifecycleOwner) {
            _homeAdapter.setData(it)
        }
        _binding.rvFeedList.adapter = _homeAdapter
    }
}