plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
}

android {
    namespace = "module.network.fake"
}

dependencies {
    compileOnly(project(":arch:NetworkLayer:api"))
}