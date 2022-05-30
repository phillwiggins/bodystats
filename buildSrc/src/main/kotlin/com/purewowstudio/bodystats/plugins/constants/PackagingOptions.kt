package com.purewowstudio.bodystats.plugins.constants

import com.android.build.gradle.internal.dsl.PackagingOptions

fun PackagingOptions.getDefaultPackagingOptions() {
    setExcludes(
        setOf(
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
            "META-INF/MANIFEST.MF",
            "META-INF/proguard/coroutines.pro",
            "META-INF/com.android.tools/proguard/coroutines.pro"
        )
    )
}