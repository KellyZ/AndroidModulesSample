plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.jetpack")
    id("module.plugin.library.compose")
    id("module.plugin.android.hilt")
}

android {
    namespace = "modules.features.foryou"
}

dependencies {
    implementation(project(":arch:DataLayer:api"))
}