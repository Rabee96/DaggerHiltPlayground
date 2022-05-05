package com.example.daggerhiltplayground.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltplayground.databinding.HomeFragmentBinding
import com.example.daggerhiltplayground.network.Network
import com.example.daggerhiltplayground.util.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding get() = _binding!!
    var p0 = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java].apply {
            if (Network.isInternetAvailable(requireContext()))
                characters(1)
            else
                internetConnectionFailure()
        }
        _binding = HomeFragmentBinding.inflate(layoutInflater).apply {
            varHomeItems = viewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvCharactersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollHorizontally(1))
                        viewModel.apply {
                            p0 =
                                (rvCharactersList.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
                            onChaneCharacterListScrollPosition(p0)
                            if (characterListScrollPosition == character.value?.data?.lastIndex && character.value!!.status != ResponseStatus.LOADING) {
                                nextPage()
                            }
                        }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.character.removeObservers(viewLifecycleOwner)
    }
}