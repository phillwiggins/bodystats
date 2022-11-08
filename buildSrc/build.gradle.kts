repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("com.android.tools.build:gradle-api:7.3.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.42")
}

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("ui-plugin") {
            id = "ui-plugin"
            implementationClass = "com.purewowstudio.bodystats.plugins.UiPlugin"
        }
    }
}

gradlePlugin {
    plugins {
        register("library-plugin") {
            id = "library-plugin"
            implementationClass = "com.purewowstudio.bodystats.plugins.LibraryPlugin"
        }
    }
}

gradlePlugin {
    plugins {
        register("app-plugin") {
            id = "app-plugin"
            implementationClass = "com.purewowstudio.bodystats.plugins.AppPlugin"
        }
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
    compileKotlin.kotlinOptions {
        languageVersion = "11"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            allWarningsAsErrors = project.hasProperty("warningsAsErrors")

            project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
                kotlinOptions.freeCompilerArgs += listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=kotlinx.coroutines.FlowPreview",
                    "-opt-in=kotlin.Experimental",
                )
            }

            jvmTarget = "11"
        }
    }
}
