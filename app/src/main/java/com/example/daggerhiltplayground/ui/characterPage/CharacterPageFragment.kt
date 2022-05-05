package com.example.daggerhiltplayground.ui.characterPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltplayground.databinding.CharacterPageFragmentBinding
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.ui.home.HomeFragment
import com.example.daggerhiltplayground.util.NavKeys.CHARACTER
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterPageFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterPageFragment()
    }

    private lateinit var viewModel: CharacterPageViewModel
    private var _binding: CharacterPageFragmentBinding? = null
    private val binding: CharacterPageFragmentBinding
        get() = _binding!!
    var characterInfoJson :String? = null
    @Inject
    lateinit var gson: Gson

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (arguments != null)
            characterInfoJson = requireArguments().getString(CHARACTER.name)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CharacterPageViewModel::class.java].apply {
            characterInfo.value = gson.fromJson(characterInfoJson, Character::class.java)
        }
        _binding = CharacterPageFragmentBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@CharacterPageFragment
            varCharacterPage = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}