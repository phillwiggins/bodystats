package com.purewowstudio.bodystats.plugins

import com.android.build.gradle.BaseExtension
import com.purewowstudio.bodystats.plugins.constants.getDefaultPackagingOptions
import com.purewowstudio.bodystats.plugins.constants.setDefaultCompileOptions
import com.purewowstudio.bodystats.plugins.constants.setExperimentalWarningsOptIn
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class LibraryPlugin : Plugin<Project> {

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
            apply(ModulePlugins.ANDROID_LIBRARY)
            apply(ModulePlugins.KOTLIN_ANDROID)
            apply(ModulePlugins.KOTLIN_KAPT)
            apply(ModulePlugins.HILT)
        }
    }

    private fun Project.androidConfig() {
        android.run {
            compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

            defaultConfig {
                minSdk = Sdk.MIN_SDK_VERSION
                targetSdk = Sdk.TARGET_SDK_VERSION

                versionCode = ModuleVersions.LIBRARY_VERSION_CODE
                versionName = ModuleVersions.LIBRARY_VERSION

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions.setDefaultCompileOptions()

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

            packagingOptions.getDefaultPackagingOptions()
            setExperimentalWarningsOptIn()
        }
    }

    private fun Project.appDependencies() {
        dependencies {
            add("implementation",Dependencies.DI.HILT)
            add("kapt", Dependencies.DI.HILT_KAPT) }
    }
}
