package com.example.socialmediaappandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.socialmediaappandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var _homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by activityViewModels()

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
        _binding.rvFeedList.adapter = _homeAdapter

        setDataAdapter()
    }

    private fun setDataAdapter() {
        homeViewModel.getFeeds().observe(viewLifecycleOwner) {
            _homeAdapter.setData(it)
        }
    }
}