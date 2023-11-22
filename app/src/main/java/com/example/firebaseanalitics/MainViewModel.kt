package com.example.firebaseanalitics

import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebaseanalitics.models.ProfileDetails
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private  val sharedPreferences: SharedPreferences
    ) : ViewModel() {
        val sharedPerf = sharedPreferences
        val sharedprefEdtior = sharedPreferences.edit()
        val user = mutableStateOf<ProfileDetails?>(null)
        val imageUri  = mutableStateOf<Uri?>(Uri.EMPTY)
        val isNight = mutableStateOf(false)

    init {
        isNight.value = getDayNightMode()
        if(sharedPreferences.getString("user_details",null) != null){
            user.value = Gson().fromJson(sharedPreferences.getString("user_details",null),ProfileDetails::class.java)
        }

    }
    fun getUser():String?{
         return  sharedPreferences.getString("user_details",null)
    }
    fun setUserDetails(user:String){
        sharedPreferences.edit().putString("user_details",user).apply()
    }

    fun setDayNightMode(boolean: Boolean){
        sharedPreferences.edit().putBoolean("dayNight",boolean).apply()
    }

    private fun getDayNightMode():Boolean{
        return sharedPreferences.getBoolean("dayNight",false)
    }
    fun getUserId(): String {
        return sharedPreferences.getString("user_id", "") ?: ""
    }


    fun setUserId(userId: String) {
        sharedPreferences.edit().putString("text", userId).apply()
    }




    //todo last name
    fun getName(): String {
        return sharedPreferences.getString("name", "") ?: ""
    }

    fun setName(text: String) {
        sharedPreferences.edit().putString("name", text).apply()
    }

    //mobile
    fun getMobileNumber(): String {
        return sharedPreferences.getString("Mobile", "") ?: ""
    }

    fun setMobile(text: String) {
        sharedPreferences.edit().putString("Mobile", text).apply()
    }
    //password

    fun getPassword(): String {
        return sharedPreferences.getString("password", "") ?: ""
    }
    fun setPassword(text: String) {
        sharedPreferences.edit().putString("password", text).apply()
    }

    //email
    fun getEmail(): String {
        return sharedPreferences.getString("email", "") ?: ""
    }
    fun setEmail(text: String) {
        sharedPreferences.edit().putString("email", text).apply()
    }

    //uri
    fun getUri():String? {

       return sharedPreferences.getString("uri","")
    }


    fun setUri(uri:String){
        sharedPreferences.edit().putString("uri",uri).apply()
    }



    fun profileDetails(user:MutableState<ProfileDetails>){

        val gson = Gson()
        val json = gson.toJson(user.value)
        sharedprefEdtior.putString("D",json).apply()
    }
}