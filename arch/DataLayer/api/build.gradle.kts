plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
}

android {
    namespace = "module.data.api"
}

dependencies {
    api(project(":arch:DataLayer:model"))
}