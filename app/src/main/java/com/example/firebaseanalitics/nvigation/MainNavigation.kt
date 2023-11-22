package com.example.firebaseanalitics.nvigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseanalitics.screens.DashBoardScreen
import com.example.firebaseanalitics.screens.LoginScreen
import com.example.firebaseanalitics.screens.RegistrationScreen
import com.example.firebaseanalitics.trackScreen
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun MainNavigation(firebaseAnalytics: FirebaseAnalytics){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =NavigationScreens.LOGIN_SCREEN.name  ){
        composable(NavigationScreens.LOGIN_SCREEN.name){
            LoginScreen(navController, firebaseAnalytics = firebaseAnalytics)
        }
        composable(NavigationScreens.REGISTRATION_SCREEN.name){

            RegistrationScreen(navController, firebaseAnalytics = firebaseAnalytics)
        }
        composable(NavigationScreens.DASHBOARD_SCREEN.name){
            DashBoardScreen(navController,firebaseAnalytics = firebaseAnalytics)
        }
    }
}