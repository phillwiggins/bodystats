version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("library-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.Domain.BASE))
    implementation(project(Dependencies.Modules.Domain.HEALTH_DATA))

    implementation("androidx.health.connect:connect-client:1.0.0-alpha07")
}
