package com.purewowstudio.bodystats.plugins

import App
import ModulePlugins
import Sdk
import Versions
import com.android.build.gradle.BaseExtension
import com.purewowstudio.bodystats.plugins.constants.setDefaultPackagingOptions
import com.purewowstudio.bodystats.plugins.constants.setExperimentalWarningsOptIn
import com.purewowstudio.bodystats.plugins.constants.setDefaultCompileOptions
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AppPlugin : Plugin<Project> {

    private val Project.android: BaseExtension
        get() = extensions.findByName("android") as? BaseExtension
            ?: error("Not an Android module: $name")

    override fun apply(project: Project) =
        with(project) {
            applyPlugins()
            androidConfig()
            appDependencies()
        }

    private fun Project.applyPlugins() {
        plugins.run {
            apply(ModulePlugins.ANDROID_APP)
            apply(ModulePlugins.KOTLIN_ANDROID)
        }
    }

    private fun Project.androidConfig() {
        android.run {
            compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

            defaultConfig {
                minSdk = Sdk.MIN_SDK_VERSION
                targetSdk = Sdk.TARGET_SDK_VERSION

                applicationId = App.APP_ID
                versionCode = App.APP_VERSION_CODE
                versionName = App.APP_VERSION_NAME
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            project.tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java)
                .configureEach {
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
                    isJniDebuggable = false
                    isRenderscriptDebuggable = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            lintOptions {
                isWarningsAsErrors = true
                isAbortOnError = true
            }

            buildFeatures.compose = true

            composeOptions {
                kotlinCompilerExtensionVersion = Versions.Compose.KOTLIN_COMPILER
            }

            setDefaultCompileOptions()
            setDefaultPackagingOptions()
            setExperimentalWarningsOptIn()
        }
    }

    private fun Project.appDependencies() {
        dependencies {
            add("coreLibraryDesugaring", Dependencies.Main.DESUGARING)
        }
    }
}
