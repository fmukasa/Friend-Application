package com.example.friendapplication.uitel

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.friendapplication.R


fun ImageView.loadImage(url: String?) {

    val option = RequestOptions().placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        // .load("Firebase_ImageURL")
        .into(this)
}