plugins {
    //alias(libs.plugins.androidApplication)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
   // alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ktlint)
}

android {

    namespace = "com.example.gamecharacters.extensions"
    compileSdk = libs.versions.compileSdk.get().toInt()

    with(defaultConfig) {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    kotlin {
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            consumerProguardFiles("proguard-rules.pro")
        }
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.kotlin.coroutines)
    //implementation(libs.kotlin.serialization)
    //implementation(libs.kotlin.serialization.converter)
    implementation(libs.lifecycle.runtime.compose)
    //implementation(libs.navigation)
   // implementation(libs.okhttp.logging.interceptor)
   // implementation(libs.retrofit)
    implementation(libs.timber)
   // androidTestImplementation(libs.bundles.common.android.test)

    detektPlugins(libs.detekt.compose.rules)
}