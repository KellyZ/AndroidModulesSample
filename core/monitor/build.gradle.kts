plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
    id("module.plugin.android.monitor")
    id("module.plugin.matrix.depends")
}

android {
    namespace = "module.core.monitor"
}

dependencies {
    implementation(project(":core:common"))
}