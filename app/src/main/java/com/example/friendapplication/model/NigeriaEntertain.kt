package com.example.friendapplication.model

import com.google.firebase.database.Exclude

data class NigeriaEntertain(
    var name:String? = null,
    var imageUrl:String? = null,
    var description: String? = null,
    @get: Exclude
    @set: Exclude
    var key : String? = null
// var description: String? = null

)
