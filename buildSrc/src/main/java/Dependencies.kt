object Libraries {

    object Versions {
        const val androidX = "1.7.0"
        const val navigation = "2.5.3"
        const val material = "1.6.1"
        const val appCompatX = "1.5.1"
    }

    const val androidXCore = "androidx.core:core-ktx:${Versions.androidX}"

    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val appCompatX = "androidx.appcompat:appcompat:${Versions.appCompatX}"
}
