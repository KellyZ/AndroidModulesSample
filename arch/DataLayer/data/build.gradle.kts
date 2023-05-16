plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
}

android {
    namespace = "module.data.repository"
}

dependencies {
//    implementation(project(":DataLayer:model"))
    compileOnly(project(":arch:DataLayer:api"))
    compileOnly(project(":arch:NetworkLayer:api"))
}