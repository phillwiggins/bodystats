version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("ui-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.UI.COMMON))
    implementation(project(Dependencies.Modules.UI.OVERVIEW))
    implementation(project(Dependencies.Modules.UI.PROFILE))
    implementation(project(Dependencies.Modules.UI.NAVIGATION))
}
