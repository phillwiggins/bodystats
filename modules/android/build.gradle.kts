version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("library-plugin")
}

android {
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))

    implementation(SupportLibs.ANDROIDX_APPCOMPAT)
    implementation(SupportLibs.ANDROIDX_CORE_KTX)

    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
}
