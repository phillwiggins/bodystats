plugins {
    id("app-plugin")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)
    implementation(SupportLibs.MATERIAL)
    implementation(SupportLibs.ANDROIDX_NAV_FRAGMENT)
    implementation(SupportLibs.ANDROIDX_NAV_UI)

    implementation(Dependencies.Compose.ACTIVITIES)
    implementation(Dependencies.Compose.MATERIAL)
    implementation(Dependencies.Compose.ANIM)
    implementation(Dependencies.Compose.VIEWMODEL)
    implementation(Dependencies.Compose.TOOLING)

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
