repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("com.android.tools.build:gradle-api:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
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
        languageVersion = "1.8"
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

            jvmTarget = "1.8"
        }
    }
}
