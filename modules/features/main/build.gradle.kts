plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation(Libraries.androidXCore)
    implementation(Libraries.appCompatX)
    implementation(Libraries.material)
    implementation(Libraries.navigationUI)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.contraintLayout)

    implementation(project(":modules:navigation"))
}