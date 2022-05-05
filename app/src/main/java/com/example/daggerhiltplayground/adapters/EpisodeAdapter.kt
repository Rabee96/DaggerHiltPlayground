package com.example.daggerhiltplayground.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltplayground.R
import com.example.daggerhiltplayground.databinding.EpisodeCardBinding
import com.example.daggerhiltplayground.util.NavKeys.*

class EpisodeAdapter(private val context: Context, private val episodeList: List<String>) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    private lateinit var _view: EpisodeCardBinding
    private val view get() = _view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapter.ViewHolder {
        _view = EpisodeCardBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeAdapter.ViewHolder, position: Int) =
        holder.bind(episodeList[position])

    inner class ViewHolder(private val itemBinding: EpisodeCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(episode: String) {
            var episodeNumber: String
            itemBinding.apply {
                episodeNumber = if (episode.takeLast(2).contains('/')){
                    episode.takeLast(1)
                }else
                    episode.takeLast(2)
                tvEpisodeNumber.text = ("Episode $episodeNumber")
                episodeCard.setOnClickListener{
                    val bundle = Bundle()
                    bundle.putString(EPISODE.name,episodeNumber)
                    it.findNavController().navigate(R.id.action_characterPageFragment_to_episodePageFragment,bundle)
               }
            }
        }
    }

    override fun getItemCount() : Int = episodeList.size
}