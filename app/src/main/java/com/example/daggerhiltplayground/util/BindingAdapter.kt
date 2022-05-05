@file:Suppress("UNCHECKED_CAST")

package com.example.daggerhiltplayground.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggerhiltplayground.adapters.EpisodeAdapter
import com.example.daggerhiltplayground.adapters.HomeAdapter
import com.example.daggerhiltplayground.pojo.character.Character
import com.example.daggerhiltplayground.util.ResponseStatus.*
import com.example.daggerhiltplayground.util.AdapterType.*


@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    if (url == null) {
        imageView.setImageDrawable(placeHolder)
    } else {
        Glide.with(imageView.context).load(url)
            .into(imageView)
    }
}

@BindingAdapter(value = ["setTextColor"])
fun setTextColor(textView: TextView, characterStatus: String) {
    if (characterStatus == "Alive") {
        textView.setTextColor(ColorStateList.valueOf(Color.GREEN))
    } else {
        textView.setTextColor(ColorStateList.valueOf(Color.RED))
    }
}

@BindingAdapter(value = ["view_visibility", "is_shimmer_view", "error"], requireAll = false)
fun viewVisibility(view: View, status: ResponseStatus?, isShimmerView: Boolean, error: Boolean) {
    when (status) {
        LOADING -> {
            if (isShimmerView)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
        SUCCESS -> {
            if (!isShimmerView) {
                if (!error)
                    view.visibility = View.VISIBLE
                else
                    view.visibility = View.GONE
            } else
                view.visibility = View.GONE
        }
        ERROR -> {
            if (!isShimmerView)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
        CANCELLED -> {
            if (!isShimmerView)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
        else -> throw (Exception("Status is $status"))
    }
}

@BindingAdapter(value = ["adapter_type", "recycler_adapter"], requireAll = true)
fun list(view: RecyclerView, type: AdapterType, list: List<*>?) {
    view.scheduleLayoutAnimation()
    if (list != null)
        when (type) {
            Home -> {
                view.adapter  = HomeAdapter(view.context,list as List<Character>,Home)
                view.scrollToPosition(list.size -20)
            }
            CharacterInfo -> {
                view.adapter =
                    EpisodeAdapter(view.context, list as List<String>)
            }
            EpisodePage -> {
                view.adapter =
                    HomeAdapter(view.context, list as List<Character>, EpisodePage)
            }
        }
}
