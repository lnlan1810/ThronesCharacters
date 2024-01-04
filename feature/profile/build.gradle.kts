plugins {
   // alias(libs.plugins.androidApplication)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.hilt)
   /* alias(libs.plugins.google.services)
    alias (libs.plugins.firebase.crashlytics)
    alias (libs.plugins.firebase.performance)*/
}

android {
    namespace = "com.example.gamecharacters.profile"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      //  consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
        jvmTarget = "17"
    }

}

dependencies {

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:extensions"))
    implementation(project(":feature:setting"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.cloudmessaging)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.dynamiclinks)
    implementation(libs.firebase.performance)
    implementation(libs.firebase.remoteconfig)

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.accompanist.swipe.refresh)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.navigation.hilt)
    implementation(libs.kotlin.coroutines)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation)
    implementation(libs.kotlin.serialization)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.codepath)
    implementation(libs.room)
    implementation(libs.coil)

    implementation(libs.timber)
    implementation(libs.lifecycle.runtime.compose)
    coreLibraryDesugaring(libs.desugar)

    detektPlugins(libs.detekt.compose.rules)

}