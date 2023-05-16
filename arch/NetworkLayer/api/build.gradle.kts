plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
    id("module.plugin.android.network")
}

android {
    namespace = "module.network.api"
}

dependencies {
    api(project(":arch:NetworkLayer:model"))
}
