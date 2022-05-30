package com.purewowstudio.bodystats.plugins.constants

import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


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