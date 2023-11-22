package com.example.firebaseanalitics
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firebaseanalitics.nvigation.MainNavigation
import com.example.firebaseanalitics.ui.theme.FirebaseAnaliticsTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        val versionName = this.packageManager.getPackageInfo("com.example.firebaseanalitics",0).versionName

        firebaseAnalytics = Firebase.analytics

        firebaseAnalytics.setUserProperty("ApplicationVersion",versionName)

        firebaseAnalytics.logEvent("App_version"){
           param(FirebaseAnalytics.Param.METHOD,"mainMethod")
        }
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAnaliticsTheme {
                MainNavigation(firebaseAnalytics)
            }
        }
    }
}

