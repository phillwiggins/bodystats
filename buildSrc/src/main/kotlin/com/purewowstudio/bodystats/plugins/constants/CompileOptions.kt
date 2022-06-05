package com.purewowstudio.bodystats.plugins.constants

import com.android.build.gradle.internal.CompileOptions
import org.gradle.api.JavaVersion

fun CompileOptions.setDefaultCompileOptions() {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}