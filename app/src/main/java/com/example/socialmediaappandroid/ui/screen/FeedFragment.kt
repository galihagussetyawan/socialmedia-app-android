package com.example.socialmediaappandroid.ui.screen

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.socialmediaappandroid.R
import com.example.socialmediaappandroid.databinding.FragmentFeedBinding
import com.example.socialmediaappandroid.model.FeedResponse
import com.example.socialmediaappandroid.ui.viewmodel.FeedViewModel
import com.example.socialmediaappandroid.ui.widget.MenuBottomSheetDialogFragment

class FeedFragment : Fragment() {
    private lateinit var _binding: FragmentFeedBinding
    private val _feedViewModel: FeedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFeedBinding.inflate(layoutInflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getString("position")?.toInt()

        _binding.feedCardItem.root.visibility = View.GONE
        _binding.progressBar.visibility = View.VISIBLE

        _feedViewModel.getFeedById(position!!).observe(viewLifecycleOwner) {
            _binding.feedCardItem.root.visibility = View.VISIBLE
            _binding.progressBar.visibility = View.GONE

            setupFeedCard(it)
            setupEmoReact(it)
            selectEmoticon(position)
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

    private fun setupEmoReact(data: FeedResponse) {
        val color = TypedValue().let {
            requireContext().theme?.resolveAttribute(android.R.attr.windowBackground, it, true)
            requireContext().getColor(it.resourceId)
        }

        when (data?.reaction?.symbol) {
            1 -> _binding.feedCardItem.btnEmo1.background.setTint(requireContext().getColor(R.color.green))
            2 -> _binding.feedCardItem.btnEmo2.background.setTint(requireContext().getColor(R.color.green))
            3 -> _binding.feedCardItem.btnEmo3.background.setTint(requireContext().getColor(R.color.green))
            4 -> _binding.feedCardItem.btnEmo4.background.setTint(requireContext().getColor(R.color.green))
            5 -> _binding.feedCardItem.btnEmo5.background.setTint(requireContext().getColor(R.color.green))
            6 -> _binding.feedCardItem.btnEmo6.background.setTint(requireContext().getColor(R.color.green))
            7 -> _binding.feedCardItem.btnEmo7.background.setTint(requireContext().getColor(R.color.green))
            else -> {
                _binding.feedCardItem.btnEmo1.background.setTint(color)
                _binding.feedCardItem.btnEmo2.background.setTint(color)
                _binding.feedCardItem.btnEmo3.background.setTint(color)
                _binding.feedCardItem.btnEmo4.background.setTint(color)
                _binding.feedCardItem.btnEmo5.background.setTint(color)
                _binding.feedCardItem.btnEmo6.background.setTint(color)
                _binding.feedCardItem.btnEmo7.background.setTint(color)
            }
        }
    }

    private fun selectEmoticon(position: Int) {
        val color = TypedValue().let {
            requireContext().theme?.resolveAttribute(android.R.attr.windowBackground, it, true)
            requireContext().getColor(it.resourceId)
        }

        _binding.feedCardItem.btnEmo1.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 1)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo2.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 2)

            _binding.feedCardItem.btnEmo1.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo3.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 3)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo1.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo4.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 4)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo1.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo5.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 5)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo1.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo6.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 6)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo1.background.setTint(color)
            _binding.feedCardItem.btnEmo7.background.setTint(color)

            notifyUpdateData(position)
        }

        _binding.feedCardItem.btnEmo7.setOnClickListener {
            _feedViewModel.selectEmoticon(position, 7)

            _binding.feedCardItem.btnEmo2.background.setTint(color)
            _binding.feedCardItem.btnEmo3.background.setTint(color)
            _binding.feedCardItem.btnEmo4.background.setTint(color)
            _binding.feedCardItem.btnEmo5.background.setTint(color)
            _binding.feedCardItem.btnEmo6.background.setTint(color)
            _binding.feedCardItem.btnEmo1.background.setTint(color)

            notifyUpdateData(position)
        }
    }

    private fun notifyUpdateData(position: Int) {
        _feedViewModel.getFeedById(position).observe(viewLifecycleOwner) {
            setupEmoReact(it)
        }
    }
}