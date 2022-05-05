package com.example.daggerhiltplayground.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggerhiltplayground.R
import com.example.daggerhiltplayground.databinding.CharacterCardBinding
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.di.GsonProviderEntry
import com.example.daggerhiltplayground.util.AdapterType
import com.example.daggerhiltplayground.util.AdapterType.Home
import com.example.daggerhiltplayground.util.AdapterType.EpisodePage
import com.example.daggerhiltplayground.util.NavKeys.CHARACTER
import com.google.gson.Gson
import dagger.hilt.android.EntryPointAccessors


class HomeAdapter(
    private val context: Context,
    private var characterList: List<Character>,
    private val adapterType: AdapterType
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var _view: CharacterCardBinding
    private val view get() = _view
    private var gson: Gson

    init {
        val appContext = context.applicationContext ?: throw IllegalStateException()
        val gsonEntryPoint =
            EntryPointAccessors.fromApplication(appContext, GsonProviderEntry::class.java)
        gson = gsonEntryPoint.gsonEntryProvider()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _view = CharacterCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(characterList[position])

    inner class ViewHolder(private val itemBinding: CharacterCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Character) {
            itemBinding.apply {
                Glide.with(context)
                    .load(item.image)
                    .into(ivCharacter)
                tvCharacterName.text = item.name
                tvDimension.text = item.origin.name
                characterCard.setOnClickListener {
                    val jsonCharacter = gson.toJson(item)
                    val bundle = Bundle()
                    bundle.putString(CHARACTER.name, jsonCharacter)
                    when (adapterType) {
                        Home -> {
                            it.findNavController()
                                .navigate(R.id.action_homeFragment_to_characterPageFragment, bundle)
                        }
                        EpisodePage -> {
                            it.findNavController().navigate(
                                R.id.action_episodePageFragment_to_characterPageFragment,
                                bundle
                            )
                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = characterList.size
}