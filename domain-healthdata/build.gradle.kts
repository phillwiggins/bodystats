version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("library-plugin")
}

dependencies {
    implementation("androidx.health.connect:connect-client:1.0.0-alpha08")
    implementation(project(Dependencies.Modules.Domain.ENTITIES))
}
