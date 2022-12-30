version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("ui-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.UI.NAVIGATION))
    implementation(project(Dependencies.Modules.UI.COMMON))
    implementation(project(Dependencies.Modules.Domain.BASE))
    implementation(project(Dependencies.Modules.Domain.STORES))
    implementation(project(Dependencies.Modules.Domain.HEALTH_DATA))
}
