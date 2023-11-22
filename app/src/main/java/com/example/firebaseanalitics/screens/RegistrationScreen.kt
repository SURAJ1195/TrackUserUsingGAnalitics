package com.example.firebaseanalitics.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firebaseanalitics.MainViewModel
import com.example.firebaseanalitics.models.ProfileDetails
import com.example.firebaseanalitics.nvigation.NavigationScreens
import com.example.firebaseanalitics.trackScreen
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    model:MainViewModel = hiltViewModel(),firebaseAnalytics: FirebaseAnalytics
) {

    LaunchedEffect(key1 = true){
        trackScreen(NavigationScreens.REGISTRATION_SCREEN.name,firebaseAnalytics)
    }

    val name = remember{ mutableStateOf("") }
    val phoneNumber = remember{ mutableStateOf("") }
    val email = remember{ mutableStateOf("") }
    val address = remember{ mutableStateOf("") }
    val pinCode = remember{ mutableStateOf("") }
    val userName = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InputField(value = name, label = "Name")
            InputField(value = phoneNumber, label = "Mobile Number")
            InputField(value = email, label = "Email")
            InputField(value = address, label = "Address")
            InputField(value = pinCode, label = "PinCode")
            InputField(value = userName, label = "User Name")
            InputField(value = password, label = "Password")

            Button {
                if(name.value.trim() ==""){
                    Toast.makeText(context,"Name should not be empty",Toast.LENGTH_SHORT).show()
                }else
                    if(phoneNumber.value.trim() ==""){
                    Toast.makeText(context,"Phone number should not be empty",Toast.LENGTH_SHORT).show()

                }
                else
                    if(email.value.trim()==""){
                    Toast.makeText(context,"Email should not be empty ",Toast.LENGTH_SHORT).show()
                }
                else
                    if(address.value.trim()==""){
                    Toast.makeText(context,"address should not be empty ",Toast.LENGTH_SHORT).show()
                }
                else
                    if(pinCode.value.trim()==""){
                    Toast.makeText(context,"PinCode should not be empty ", Toast.LENGTH_SHORT).show()
                }else
                    if(userName.value.trim()==""){
                    Toast.makeText(context,"User Name should not be empty ", Toast.LENGTH_SHORT).show()
                }else
                    if(password.value.trim()==""){
                    Toast.makeText(context,"password should not be empty ", Toast.LENGTH_SHORT).show()
                }

                else {
                    val user = ProfileDetails(
                        userName = userName.value.trim(),
                        password = password.value.trim(),
                        mobileNumber = phoneNumber.value.trim(),
                        name = name.value.trim(),
                        email = email.value.trim(),
                        address = address.value.trim()

                    )
                    val gson = Gson()
                    val gsonData = gson.toJson(user,ProfileDetails::class.java)
                    model.setUserDetails(gsonData)
                    Toast.makeText(context,"User Data Saved Successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    navController.navigate(NavigationScreens.LOGIN_SCREEN.name)

                }
            }
        }
    }

}