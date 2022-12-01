version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("ui-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.Domain.HEALTH_DATA))
}
