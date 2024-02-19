import dev.icerock.gradle.MRVisibility

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.moko.resources.plugin)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.java.version.int.get()
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    multiplatformResources {
        resourcesPackage = "ru.braveowlet.features.first_screen.impl"
        resourcesVisibility = MRVisibility.Public
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.logger)
            implementation(projects.common.mvi.mviGeneral)
            implementation(projects.common.mvi.mviCompose)
            implementation(projects.core.recources)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.koin.core)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)

            implementation(libs.voyager.koin)
            implementation(libs.voyager.screenModel)

            implementation(libs.moko.resources)
            implementation(libs.moko.resources.compose)
        }
    }
}

android {
    namespace = "ru.braveowlet.kmmpr.features.first_screen.impl"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.java.version.string.get())
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
