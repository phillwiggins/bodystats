version = ModuleVersions.LIBRARY_VERSION

plugins {
    id("library-plugin")
}

android {
}

dependencies {
    testImplementation(TestingLib.JUNIT)

    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(AndroidTestingLib.ANDROIDX_TEST_EXT_JUNIT)
}
