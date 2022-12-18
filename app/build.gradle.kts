plugins {
    id("app-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.UI.MAIN))
    implementation(project(Dependencies.Modules.UI.WIDGETS))
    implementation(project(Dependencies.Modules.UI.NAVIGATION))

    implementation(project(Dependencies.Modules.Domain.ENTITIES))
    implementation(project(Dependencies.Modules.Domain.HEALTH_DATA))
    implementation(project(Dependencies.Modules.Domain.BASE))
    implementation(project(Dependencies.Modules.Domain.STORES))

    implementation(project(Dependencies.Modules.Data.STORES))
    implementation(project(Dependencies.Modules.Data.HEALTH_CONNECT))

    implementation(Dependencies.Main.ANDROIDX_NAV_UI)
    implementation(Dependencies.Main.MATERIAL)

    implementation(Dependencies.Compose.ACTIVITIES)
    implementation(Dependencies.Compose.TOOLING)

    implementation(project(Dependencies.Modules.Data.HEALTH_CONNECT))
    implementation(project(Dependencies.Modules.Domain.HEALTH_DATA))

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
