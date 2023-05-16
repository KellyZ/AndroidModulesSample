plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.hilt")
    id("module.plugin.library.test")
    id("module.plugin.android.network")
}

android {
    namespace = "module.network.fake"
}

dependencies {
    compileOnly(project(":arch:NetworkLayer:api"))
    testImplementation(project(":arch:NetworkLayer:api"))
    testImplementation(project(":arch:NetworkLayer:fake"))
    testImplementation(project(":arch:NetworkLayer:retrofit"))
    implementation(project(":arch:NetworkLayer:fake"))
}