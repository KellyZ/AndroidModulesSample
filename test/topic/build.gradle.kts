plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.jetpack")
    id("module.plugin.library.test")
}

android {
    namespace = "module.test.topic"
}

dependencies {
    implementation(project(":feature:topic"))
    implementation(project(":arch:DataLayer:api"))
    implementation(project(":test:data"))
    implementation(project(":test:common"))
}