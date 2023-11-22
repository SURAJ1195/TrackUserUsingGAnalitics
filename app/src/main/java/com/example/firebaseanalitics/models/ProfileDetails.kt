package com.example.firebaseanalitics.models

import android.net.Uri

data class ProfileDetails(
    val userName : String? = "",
    val name : String? = "",
    val mobileNumber : String? = "",
    val password : String? ="",
    val email : String? = "",
    val address : String? = ""
)