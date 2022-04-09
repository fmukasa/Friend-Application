package com.example.friendapplication.model

import com.google.firebase.database.Exclude

data class KenyaEvent(
    var name:String? = null,
    var imageUrl:String? = null,
    var description: String? = null,
    @get: Exclude
    @set: Exclude
    var key : String? = null
// var description: String? = null
)
