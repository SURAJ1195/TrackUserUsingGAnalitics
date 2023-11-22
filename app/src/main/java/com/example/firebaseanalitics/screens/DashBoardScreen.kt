package com.example.firebaseanalitics.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.example.firebaseanalitics.nvigation.NavigationScreens
import com.example.firebaseanalitics.trackScreen
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun DashBoardScreen(navController: NavHostController,firebaseAnalytics: FirebaseAnalytics) {
    LaunchedEffect(key1 = true){
        trackScreen(NavigationScreens.DASHBOARD_SCREEN.name,firebaseAnalytics)
    }
}