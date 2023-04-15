package com.example.socialmediaappandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.databinding.FragmentFeedBinding
import com.example.socialmediaappandroid.model.FeedResponse
import com.example.socialmediaappandroid.ui.viewmodel.AuthViewModel
import com.example.socialmediaappandroid.ui.viewmodel.FeedViewModel
import com.example.socialmediaappandroid.ui.widget.MenuBottomSheetDialogFragment

class FeedFragment : Fragment() {
    private lateinit var _binding: FragmentFeedBinding
    private val _feedViewModel: FeedViewModel by activityViewModels()
    private lateinit var _authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("id").toString()

        _binding.feedCardItem.root.visibility = View.GONE
        _binding.progressBar.visibility = View.VISIBLE

        _authViewModel.getCurrentUser().observe(viewLifecycleOwner) {
            _feedViewModel.getFeedById(id, it?.uid.toString())
                .observe(viewLifecycleOwner) { feedData ->
                    _binding.feedCardItem.root.visibility = View.VISIBLE
                    _binding.progressBar.visibility = View.GONE

                    setupFeedCard(feedData)
                }
        }

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

    private fun setupFeedCard(data: FeedResponse) {
        _binding.feedCardItem.displayName.text = data?.user?.displayName
        _binding.feedCardItem.textBody.text = data?.feed?.text

        _binding.feedCardItem.displayName.setOnClickListener() {
            findNavController().navigate(R.id.profileFragment)
        }

        setupAvatarImage(data.user?.photoURL.toString())
        setupImageCollection(data)
    }

    private fun setupAvatarImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(_binding.feedCardItem.avatarImage)
    }

    private fun setupImageCollection(data: FeedResponse) {
        if (data.images!!.isEmpty()) {
            _binding.feedCardItem.flImageCollection.visibility = View.GONE
        }

        if (data.images!!.size == 1) {
            _binding.feedCardItem.cardSingleImage.root.visibility = View.VISIBLE

            Glide.with(this)
                .load(data.images!![0].url)
                .into(_binding.feedCardItem.cardSingleImage.ivFirstImage)
        }

        if (data.images!!.size == 2) {
            _binding.feedCardItem.cardDoubleImage.root.visibility = View.VISIBLE

            Glide.with(this)
                .load(data.images!![0].url)
                .into(_binding.feedCardItem.cardDoubleImage.ivFirstImage)

            Glide.with(this)
                .load(data.images!![1].url)
                .into(_binding.feedCardItem.cardDoubleImage.ivSecondImage)
        }

        if (data.images!!.size == 3) {
            _binding.feedCardItem.cardTripleImage.root.visibility = View.VISIBLE

            Glide.with(this)
                .load(data.images!![0].url)
                .into(_binding.feedCardItem.cardTripleImage.ivFirstImage)

            Glide.with(this)
                .load(data.images!![1].url)
                .into(_binding.feedCardItem.cardTripleImage.ivSecondImage)

            Glide.with(this)
                .load(data.images!![2].url)
                .into(_binding.feedCardItem.cardTripleImage.ivThirdImage)
        }

        if (data.images!!.size == 4) {
            _binding.feedCardItem.cardQuadImage.root.visibility = View.VISIBLE

            Glide.with(this)
                .load(data.images!![0].url)
                .into(_binding.feedCardItem.cardQuadImage.ivFirstImage)

            Glide.with(this)
                .load(data.images!![1].url)
                .into(_binding.feedCardItem.cardQuadImage.ivSecondImage)

            Glide.with(this)
                .load(data.images!![2].url)
                .into(_binding.feedCardItem.cardQuadImage.ivThirdImage)

            Glide.with(this)
                .load(data.images!![3].url)
                .into(_binding.feedCardItem.cardQuadImage.ivFourthImage)
        }

        if (data.images!!.size > 4) {
            _binding.feedCardItem.cardMoreImage.root.visibility = View.VISIBLE

            Glide.with(this)
                .load(data.images!![0].url)
                .into(_binding.feedCardItem.cardMoreImage.ivFirstImage)

            Glide.with(this)
                .load(data.images!![1].url)
                .into(_binding.feedCardItem.cardMoreImage.ivSecondImage)

            Glide.with(this)
                .load(data.images!![2].url)
                .into(_binding.feedCardItem.cardMoreImage.ivThirdImage)

            Glide.with(this)
                .load(data.images!![3].url)
                .into(_binding.feedCardItem.cardMoreImage.ivMoreImage)
        }
    }
}