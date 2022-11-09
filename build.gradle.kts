// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    id ("com.android.application") version ("7.2.1") apply false
    id ("com.android.library") version ("7.2.1") apply false
    id ("org.jetbrains.kotlin.android") version ("1.6.10") apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
subprojects {
    plugins.withType<com.android.build.gradle.internal.plugins.BasePlugin<*,*, *, *>> {
        configure<com.android.build.gradle.BaseExtension> {
            compileSdkVersion(33)
            defaultConfig {
                minSdk = 21
                targetSdk = 33
            }
            compileOptions {
                targetCompatibility = JavaVersion.VERSION_1_8
                sourceCompatibility = JavaVersion.VERSION_1_8
            }

            tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java)
                .configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }
        }
    }
}
