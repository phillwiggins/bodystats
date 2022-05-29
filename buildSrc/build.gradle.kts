repositories {
    jcenter()
}

plugins {
    `kotlin-dsl`
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

repositories {
    jcenter()
    google()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.8"
}

dependencies {
    implementation("com.android.tools.build:gradle:7.2.1")
    implementation("com.android.tools.build:gradle-api:7.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}
