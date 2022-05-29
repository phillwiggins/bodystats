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
            implementationClass = "com.purewowstudio.template.plugins.LibraryPlugin"
        }
    }
}

gradlePlugin {
    plugins {
        register("app-plugin") {
            id = "app-plugin"
            implementationClass = "com.purewowstudio.template.plugins.AppPlugin"
        }
    }
}

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    }
}

repositories {
    jcenter()
    google()
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.3"
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
    implementation("com.android.tools.build:gradle-api:4.0.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
}
