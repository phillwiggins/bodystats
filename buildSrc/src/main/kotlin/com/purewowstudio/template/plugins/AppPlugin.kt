package com.purewowstudio.template.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppPlugin : Plugin<Project> {

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
            apply(ModulePlugins.ANDROID_APP)
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

                applicationId = App.APP_ID
                versionCode = App.APP_VERSION_CODE
                versionName = App.APP_VERSION_NAME
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            project.tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
            sourceSets {
                getByName("main").java.srcDirs("src/main/kotlin")
            }
            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    isDebuggable = true
                    applicationIdSuffix = ".debug"
                    versionNameSuffix = "-dev"
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isDebuggable = false
                    isShrinkResources = true
                    isZipAlignEnabled = true
                    isJniDebuggable = false
                    isRenderscriptDebuggable = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }

            lintOptions {
                isWarningsAsErrors = true
                isAbortOnError = true
            }
        }
    }
}
