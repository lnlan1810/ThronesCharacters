package com.example.gamecharacters

import android.app.Application
import com.example.gamecharacters.network.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // Khởi tạo Firebase
        FirebaseApp.initializeApp(this)

        // Khởi tạo Firebase Analytics
        val analytics = FirebaseAnalytics.getInstance(this)

        // Khởi tạo Firebase Crashlytics
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCrashlyticsCollectionEnabled(true)

        // Khởi tạo Firebase Performance Monitoring
        val performance = FirebasePerformance.getInstance()
        performance.isPerformanceCollectionEnabled = true
    }
}
