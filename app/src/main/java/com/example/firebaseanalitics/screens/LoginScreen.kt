package com.example.firebaseanalitics.screens

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.firebaseanalitics.MainViewModel
import com.example.firebaseanalitics.R
import com.example.firebaseanalitics.nvigation.NavigationScreens
import com.example.firebaseanalitics.trackScreen
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel:MainViewModel = hiltViewModel(),firebaseAnalytics: FirebaseAnalytics
) {

    LaunchedEffect(key1 = true){
        trackScreen(NavigationScreens.REGISTRATION_SCREEN.name,firebaseAnalytics)
    }
    val context  = LocalContext.current
    val userName = remember{ mutableStateOf("") }
    val  password = remember{ mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.5f else 1f)

    val dayNight = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize(),

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InputField(value = userName  , label ="First Name")

            InputField(value =password , label ="Password" )


            Button{
                if(userName.value==""){
                    Toast.makeText(context, "UserName must not be empty", Toast.LENGTH_LONG).show()

                }
                else if(password.value == ""){
                    Toast.makeText(context, "password should not be empty ", Toast.LENGTH_LONG).show()
                }
                else {
                    if(userName.value.trim() != viewModel.user.value?.userName?.trim()){
                        Toast.makeText(context, "username not found",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        if(password.value.trim() != viewModel.user.value?.password?.trim()){
                            Toast.makeText(context, "wrong password",Toast.LENGTH_SHORT).show()

                        }
                        else{
                            Toast.makeText(context,"Login successful",Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                            navController.navigate(NavigationScreens.DASHBOARD_SCREEN.name)


                        }

                    }

                }

            }
            IconButton(onClick = {
                dayNight.value = !dayNight.value
            },
                interactionSource = interactionSource,
                modifier = Modifier.graphicsLayer
                {
                    scaleX = sizeScale
                    scaleY = sizeScale
                })
            {
                Icon(
                    ImageVector.vectorResource(
                    id = if(viewModel.isNight.value) R.drawable.baseline_bedtime_off_24 else R.drawable.baseline_bedtime_24) ,
                    contentDescription = "")
            }
            Text("New Register",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Cursive
                ),
                modifier = Modifier.clickable{
                    navController.popBackStack()
                    navController.navigate(NavigationScreens.REGISTRATION_SCREEN.name)

                }
            )


        }
    }

}