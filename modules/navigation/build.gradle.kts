plugins {
    id("com.android.library")
    id("androidx.navigation.safeargs")
    kotlin("android")
}

dependencies {
    implementation(Libraries.navigationUI)
    implementation(Libraries.navigationFragment)
}