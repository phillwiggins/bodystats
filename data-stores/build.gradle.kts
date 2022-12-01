version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("library-plugin")
}

dependencies {
    implementation(project(Dependencies.Modules.Domain.ENTITIES))
    implementation(project(Dependencies.Modules.Domain.STORES))
    implementation(Dependencies.Main.DATA_STORE)
}