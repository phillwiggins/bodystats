version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("ui-plugin")
}

dependencies {
    implementation(Dependencies.Compose.ACTIVITIES)
    implementation(Dependencies.Compose.MATERIAL)
    implementation(Dependencies.Compose.MATERIAL3)
    implementation(Dependencies.Compose.FONTS)
    implementation(Dependencies.Compose.ANIM)
    implementation(Dependencies.Compose.VIEWMODEL)
    implementation(Dependencies.Compose.TOOLING)
}
