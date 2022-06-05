object Sdk {
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 31
    const val COMPILE_SDK_VERSION = 31
}

object Versions {
    const val NAVIGATION = "2.4.0"
    const val MATERIAL = "1.6.1"
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val CORE_KTX = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13"
    const val KTLINT = "0.37.2"

    object Compose {
        const val KOTLIN_COMPILER = "1.1.1"
    }
}

object BuildPluginsVersion {
    const val DETEKT = "1.10.0"
    const val KTLINT = "9.2.1"
}

object ModulePlugins {
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_APP = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
}


object Dependencies {

    object Main {
        const val DESUGARING = "com.android.tools:desugar_jdk_libs:1.1.5"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val ANDROIDX_NAV_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    }

    object Modules {
        object UI {
            const val MAIN = ":ui-feat-main"
            const val OVERVIEW = ":ui-feat-overview"
            const val PROFILE = ":ui-feat-profile"
            const val COMMON = ":ui-lib-common"
            const val NAVIGATION = ":ui-lib-navigation"
        }
    }

    object Compose {
        private const val VERSION = "1.2.0-beta02"

        const val ACTIVITIES = "androidx.activity:activity-compose:1.4.0"
        const val MATERIAL = "androidx.compose.material:material:$VERSION"
        const val MATERIAL3 = "androidx.compose.material3:material3:1.0.0-alpha10"
        const val ANIM = "androidx.compose.animation:animation:$VERSION"
        const val TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
        const val FONTS = "androidx.compose.ui:ui-text-google-fonts:$VERSION"
        const val UI_TEST = "androidx.compose.ui:ui-test-junit4:$VERSION"
        const val NAVIGATION = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
        const val ICONS = "androidx.compose.material:material-icons-extended:$VERSION"
    }
}

object TestingLib {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTestingLib {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}