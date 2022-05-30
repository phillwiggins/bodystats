plugins {
    id("app-plugin")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(Dependencies.Main.ANDROIDX_NAV_UI)
    implementation(Dependencies.Main.MATERIAL)

    implementation(Dependencies.Compose.ACTIVITIES)
    implementation(Dependencies.Compose.TOOLING)
    implementation(Dependencies.Compose.MATERIAL)

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingLib.ESPRESSO_CORE)
    androidTestImplementation(Dependencies.Compose.UI_TEST)
}

tasks.register("installGitHook", Copy::class.java) {
    from("${rootProject.rootDir}/config/commit/pre-commit")
    into("${rootProject.rootDir}/.git/hooks")
}

tasks.getByPath(":app:preBuild").dependsOn("installGitHook")
