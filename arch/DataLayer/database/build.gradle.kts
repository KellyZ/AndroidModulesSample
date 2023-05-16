plugins {
    id("module.plugin.android.library")
    id("module.plugin.android.jetpack")
    id("module.plugin.android.database")
    id("module.plugin.android.hilt")
}

android {
    namespace = "com.panda.template.database"
}

dependencies {
    compileOnly(project(":arch:DataLayer:api"))

    implementation(libs.androidx.work.runtime.ktx)
}