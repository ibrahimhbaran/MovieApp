package com.dex.movieapp.view.adapter.BindingAdapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imgUrl")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}