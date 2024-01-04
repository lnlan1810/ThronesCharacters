plugins {
    //alias(libs.plugins.androidApplication)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.example.gamecharacters.home"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles("consumer-rules.pro")
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

    kotlin {
        jvmToolchain(17)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }

}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:extensions"))

    //implementation(libs.androidx.core.ktx)
    //implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
   // androidTestImplementation(libs.androidx.espresso.core)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.accompanist.swipe.refresh)
    implementation(libs.coil)
    implementation(libs.androidx.material3)
    implementation(libs.hilt)
    implementation(libs.kotlin.coroutines)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation)
    implementation(libs.navigation.hilt)
    implementation(libs.kotlin.serialization)
    implementation(libs.retrofit)
    implementation(libs.room)
    implementation(libs.timber)
    kapt(libs.hilt.compiler)
    kaptAndroidTest(libs.test.android.hilt.compiler)
    testImplementation(libs.bundles.common.test)
    androidTestImplementation(libs.bundles.common.android.test)
    coreLibraryDesugaring(libs.desugar)

    detektPlugins(libs.detekt.compose.rules)

}