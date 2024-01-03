@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)

}

android {
    namespace = "com.example.gamecharacters"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.gamecharacters"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 2
        versionName = "2.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // TODO: for development purposes, remember to create a release signing config when releasing proper app
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeKotlinCompiler.get()
    }
    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":core:base"))
    implementation(project(":core:di"))
    implementation(project(":core:extensions"))
    implementation(project(":core:navigation"))
    implementation(project(":core:designsystem"))

    implementation(project(":feature:home"))
    implementation(project(":feature:find"))
    implementation(project(":feature:setting"))
    implementation(project(":feature:profile"))

    implementation(libs.hilt)
    implementation(libs.navigation)
    implementation(libs.room.ktx)
    implementation(libs.timber)

    kapt(libs.hilt.compiler)
    ksp(libs.room.compiler)

    coreLibraryDesugaring(libs.desugar)

    detektPlugins(libs.detekt.compose.rules)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.material)
    testImplementation(libs.junit)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation)
    implementation(libs.navigation.hilt)
    implementation(libs.kotlin.serialization)
    implementation(libs.timber)
    implementation(libs.lifecycle.runtime.compose)


    kapt(libs.hilt.compiler)
    //kaptAndroidTest(libs.test.android.hilt.compiler)

    coreLibraryDesugaring(libs.desugar)

    detektPlugins(libs.detekt.compose.rules)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}
