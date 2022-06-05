package com.purewowstudio.bodystats.plugins.constants

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Not an Android module: $name")

fun Project.setExperimentalWarningsOptIn() {
    project.tasks.withType<KotlinCompile>().all {
        kotlinOptions.freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlin.Experimental",
        )
    }
}

fun Project.setDefaultPackagingOptions() {
    android.run {
        packagingOptions.getDefaultPackagingOptions()
    }
}

fun Project.setDefaultCompileOptions() {
    android.run {
        compileOptions.setDefaultCompileOptions()
    }
}