plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
    id("module.plugin.library.test")
}

android {
    namespace = "module.test.data"

    defaultConfig {
        testInstrumentationRunner = "module.test.data.utilities.MainTestRunner"
    }
}

dependencies {
    implementation(project(":arch:DataLayer:api"))
    implementation(project(":arch:DataLayer:fake"))
    implementation(project(":arch:DataLayer:database"))
}