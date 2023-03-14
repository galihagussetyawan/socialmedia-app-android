package com.example.socialmediaappandroid.ui.screen

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.socialmediaappandroid.R
import com.example.socialmediaappandroid.databinding.BottomSheetPermissionLocationBinding
import com.example.socialmediaappandroid.databinding.FragmentHomeBinding
import com.example.socialmediaappandroid.ui.adapter.HomeAdapter
import com.example.socialmediaappandroid.ui.viewmodel.AuthViewModel
import com.example.socialmediaappandroid.ui.viewmodel.FeedViewModel
import com.example.socialmediaappandroid.utils.PermissionUtils
import com.google.android.gms.location.*
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private lateinit var _homeAdapter: HomeAdapter
    private val feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var authViewModel: AuthViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationData = MutableLiveData<Location>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBottomSheetPermissionLocation()
        setupAdapter()
        getLocationListener()
        setupSearchView()
        setupBanner()
    }

    override fun onResume() {
        super.onResume()
        getLocationListener()
    }

    private fun setupSearchView() {
        _binding.appBarLayout.btnSearch.setOnClickListener {
            _binding.searchView.show()
        }
    }

    private fun setupAdapter() {
        _homeAdapter = HomeAdapter(requireActivity())
        _binding.rvFeedList.adapter = _homeAdapter

        setDataAdapter()
    }

    private fun setDataAdapter() {
        locationData.observe(viewLifecycleOwner) { loc ->
            if (loc != null) {
                _binding.rvFeedList.visibility = View.GONE
                _binding.progressBar.visibility = View.VISIBLE

                authViewModel.getCurrentUser().observe(viewLifecycleOwner) { auth ->
                    feedViewModel.getFeeds(loc.latitude, loc.longitude, auth?.uid.toString())
                        .observe(viewLifecycleOwner) {
                            _homeAdapter.setData(it)
                            _binding.progressBar.visibility = View.GONE
                            _binding.rvFeedList.visibility = View.VISIBLE
                        }
                }
            }
        }
    }

    private fun getLocationListener() {
        if (checkPermissionLocation()) {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {

        fusedLocationClient.requestLocationUpdates(
            LocationRequest(),
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        locationData.postValue(location)
                    }
                }
            },
            Looper.myLooper()
        )
    }

    private fun checkPermissionLocation(): Boolean {
        return PermissionUtils.isAccessFineLocationGranted(requireActivity()) && PermissionUtils.isLocationEnabled(
            requireActivity()
        )
    }

    private fun requestPermissionLocation(): Boolean {

        if (!PermissionUtils.isAccessFineLocationGranted(requireActivity())) {
            PermissionUtils.requestAccessFineLocationPermission(requireActivity(), 1)
        }

        if (!PermissionUtils.isLocationEnabled(requireActivity())) {
            PermissionUtils.showGPSNotEnabledDialog(requireActivity())
        }

        return true
    }

    private fun setupBottomSheetPermissionLocation() {
        val view = BottomSheetPermissionLocationBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(view.root)

        if (checkPermissionLocation()) {
            dialog.dismiss()
        } else {
            dialog.show()
            view.btnAcceptPermissionLocation.setOnClickListener {
                requestPermissionLocation()
                dialog.dismiss()
            }
        }
    }

    private fun setupBanner() {

        authViewModel.getCurrentUser().observe(viewLifecycleOwner) {

            if (it != null) {
                _binding.btnLogin.text = "Logout"
                _binding.btnLogin.setOnClickListener {
                    authViewModel.signOut()
                }
            } else {
                _binding.btnLogin.text = "Daftar"
                _binding.btnLogin.setOnClickListener {
                    findNavController().navigate(R.id.loginFragment)
                }
            }
        }

    }
}