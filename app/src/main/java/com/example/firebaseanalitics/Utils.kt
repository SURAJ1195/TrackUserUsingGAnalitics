package com.example.firebaseanalitics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun trackScreen(screenName: String,firebaseAnalytics: FirebaseAnalytics) {
    val bundle = Bundle()
    bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
//    bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "LoginActivity")
    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
}