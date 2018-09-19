package com.dex.movieapp.view.adapter.BindingAdapters

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.dex.movieapp.R
import com.dex.movieapp.utils.GlideApp
import com.dex.movieapp.utils.IMAGE_BASE_URL


@BindingAdapter("imgUrl")
fun loadImage(imageView: ImageView, url: String) {

    GlideApp.with(imageView.context)
            .load(IMAGE_BASE_URL + url)
            .placeholder(imageView.context.resources.getDrawable(R.drawable.placeholder))
            .into(imageView)


}