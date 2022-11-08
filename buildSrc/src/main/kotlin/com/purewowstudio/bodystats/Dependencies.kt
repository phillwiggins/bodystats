object Sdk {
    const val MIN_SDK_VERSION = 27
    const val TARGET_SDK_VERSION = 33
    const val COMPILE_SDK_VERSION = 33
}

object Versions {
    const val NAVIGATION = "2.5.3"
    const val MATERIAL = "1.7.0"
    const val ANDROIDX_TEST_EXT = "1.1.1"
    const val ANDROIDX_TEST = "1.2.0"
    const val ESPRESSO_CORE = "3.2.0"
    const val JUNIT = "4.13.2"
    const val KTLINT = "0.37.2"

    object Compose {
        const val KOTLIN_COMPILER = "1.3.2"
    }
}

object BuildPluginsVersion {
    const val DETEKT = "1.21.0"
    const val KTLINT = "11.0.0"
    const val VERSIONS = "0.43.0"
}

object ModulePlugins {
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_APP = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val HILT = "com.google.dagger.hilt.android"
}

object Dependencies {

    object Main {
        const val MATERIAL = "com.google.android.material:material:1.7.0"
        const val ANDROIDX_NAV_UI = "androidx.navigation:navigation-ui-ktx:2.5.3"
    }

    object DI {
        const val HILT = "com.google.dagger:hilt-android:2.44"
        const val HILT_KAPT = "com.google.dagger:hilt-android-compiler:2.44"
    }

    object Modules {
        object UI {
            const val MAIN = ":ui-feat-main"
            const val OVERVIEW = ":ui-feat-overview"
            const val PROFILE = ":ui-feat-profile"
            const val COMMON = ":ui-lib-common"
            const val NAVIGATION = ":ui-lib-navigation"
        }

        object Domain {
            const val BASE = ":domain-base"
            const val ENTITIES = ":domain-entities"
            const val HEALTH_DATA = ":domain-healthdata"
        }

        object Data {
            const val HEALTH_CONNECT = ":data-healthconnect"
        }
    }

    object Compose {
        const val ACTIVITIES = "androidx.activity:activity-compose:1.6.1"
        const val MATERIAL = "androidx.compose.material:material:1.3.0"
        const val MATERIAL3 = "androidx.compose.material3:material3:1.0.0"
        const val ANIM = "androidx.compose.animation:animation:1.3.0"
        const val TOOLING = "androidx.compose.ui:ui-tooling:1.3.0"
        const val FONTS = "androidx.compose.ui:ui-text-google-fonts:1.3.0"
        const val UI_TEST = "androidx.compose.ui:ui-test-junit4:1.3.0"
        const val NAVIGATION = "androidx.navigation:navigation-compose:2.5.3"
        const val NAVIGATION_HILT = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
        const val ICONS = "androidx.compose.material:material-icons-extended:1.3.0"
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