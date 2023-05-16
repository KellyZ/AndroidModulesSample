plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.network")
    id("module.plugin.android.hilt")
}

android {
    namespace = "com.panda.template.network.retrofit"
}
dependencies {
    compileOnly(project(":arch:NetworkLayer:api"))
}

