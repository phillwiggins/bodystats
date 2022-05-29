package com.purewowstudio.template.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryPlugin : Plugin<Project> {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
                ?: error("Not an Android module: $name")

    override fun apply(project: Project) =
            with(project) {
                applyPlugins()
                androidConfig()
            }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(ModulePlugins.ANDROID_LIBRARY)
            apply(ModulePlugins.KOTLIN_ANDROID)
            apply(ModulePlugins.KOTLIN_ANDROID_EXT)
        }
    }

    private fun Project.androidConfig() {
        android.run {
            compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

            defaultConfig {
                minSdkVersion(Sdk.MIN_SDK_VERSION)
                targetSdkVersion(Sdk.TARGET_SDK_VERSION)

                versionCode = ModuleVersions.LIBRARY_VERSION_CODE
                versionName = ModuleVersions.LIBRARY_VERSION

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                    )
                }
            }

            sourceSets {
                getByName("main").java.srcDirs("src/main/kotlin")
            }

            lintOptions {
                isWarningsAsErrors = true
                isAbortOnError = true
            }
        }
    }
}
