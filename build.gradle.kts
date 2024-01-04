plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.junit) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.appdistribution) apply false

}

allprojects {
    apply(
        plugin = "io.gitlab.arturbosch.detekt"
    )

    detekt {
        buildUponDefaultConfig = true
        config = files("$rootDir/gradle/detekt.yml")

    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

/*
buildscript {
    dependencies{
        classpath(libs.)
    }
}*/
