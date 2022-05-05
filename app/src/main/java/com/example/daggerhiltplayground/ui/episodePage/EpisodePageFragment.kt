package com.example.daggerhiltplayground.ui.episodePage

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.daggerhiltplayground.databinding.EpisodePageFragmentBinding
import com.example.daggerhiltplayground.ui.home.HomeViewModel
import com.example.daggerhiltplayground.util.NavKeys.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodePageFragment : Fragment() {

    companion object {
        fun newInstance() = EpisodePageFragment()
    }

    private lateinit var viewModel: EpisodePageViewModel
    private var _binding: EpisodePageFragmentBinding? = null
    private val binding: EpisodePageFragmentBinding
        get() = _binding!!
    lateinit var episodeNumber: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null)
            episodeNumber = requireArguments().getString(EPISODE.name, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EpisodePageViewModel::class.java].apply {
            getEpisode(episodeNumber)
        }
        _binding = EpisodePageFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@EpisodePageFragment
            varEpisodePage = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}