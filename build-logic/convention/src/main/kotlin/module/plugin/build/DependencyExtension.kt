package module.plugin.build

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.kotlin

fun DependencyHandlerScope.kotlinProject(libs: VersionCatalog) {
    "implementation"(libs.findLibrary("androidx-core-ktx").get())
    "implementation"(libs.findLibrary("startup").get())
}

fun DependencyHandlerScope.jetpackProject(libs: VersionCatalog) {
    "implementation"(libs.findLibrary("androidx-lifecycle-runtime").get())
    "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel").get())
    "implementation"(libs.findLibrary("androidx-lifecycle-livedata").get())
    "implementation"(libs.findLibrary("androidx-hilt-navigation-compose").get())
    "implementation"(libs.findLibrary("androidx-navigation-compose").get())
    "implementation"(libs.findLibrary("androidx-work-runtime-ktx").get())
}

fun DependencyHandlerScope.composeProject(libs: VersionCatalog) {
    "implementation"(platform(libs.findLibrary("compose-bom").get()))
    "implementation"(libs.findBundle("androidx-compose").get())
    "implementation"(libs.findBundle("coil-bundle").get())
}

fun DependencyHandlerScope.hiltProject(libs: VersionCatalog) {
    "implementation"(libs.findLibrary("hilt.android").get())
    "kapt"(libs.findLibrary("hilt.compiler").get())
    "kaptAndroidTest"(libs.findLibrary("hilt.compiler").get())
}

fun DependencyHandlerScope.databaseProject(libs: VersionCatalog) {
    "implementation"(libs.findLibrary("androidx-room-ktx").get())
    "kapt"(libs.findLibrary("androidx-room-compiler").get())
}

fun DependencyHandlerScope.networkProject(libs: VersionCatalog) {
    "implementation"(platform(libs.findLibrary("okhttp-bom").get()))
    "implementation"(libs.findLibrary("okhttp-logging").get())
    "implementation"(libs.findLibrary("retrofit-core").get())
    "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
    "implementation"(libs.findLibrary("retrofit-serialization-json").get())
    "implementation"(libs.findBundle("coil-bundle").get())
}

fun DependencyHandlerScope.testProject(libs: VersionCatalog, isTestApp: Boolean) {
    if (!isTestApp) {
        "testImplementation"(kotlin("test"))
        "androidTestImplementation"(kotlin("test"))
        "testImplementation"(libs.findLibrary("junit").get())
        "testImplementation"(libs.findLibrary("kotlinx-coroutines-test").get())
        "androidTestImplementation"(libs.findBundle("androidx-test").get())
        "androidTestImplementation"(libs.findLibrary("androidx-navigation-testing").get())
        "androidTestImplementation"(libs.findLibrary("androidx-work-testing").get())
    }
    "implementation"(libs.findLibrary("kotlinx-coroutines-test").get())
    "implementation"(libs.findBundle("androidx-test").get())
    "implementation"(libs.findLibrary("hilt-android-testing").get())
    "implementation"(libs.findLibrary("androidx-work-testing").get())
}

fun DependencyHandlerScope.testComposeProject(libs: VersionCatalog, isTestApp: Boolean) {
    "debugImplementation"(platform(libs.findLibrary("compose-bom").get()))
    "debugImplementation"(libs.findLibrary("compose-ui-tooling").get())
    "debugImplementation"(libs.findLibrary("compose-ui-manifest").get())
    if (!isTestApp) {
        "androidTestImplementation"(platform(libs.findLibrary("compose-bom").get()))
        "androidTestImplementation"(libs.findLibrary("compose-ui-test-junit").get())
    }
}

fun DependencyHandlerScope.commonUtilProject(libs: VersionCatalog) {
    "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
    "implementation"(libs.findLibrary("kotlinx-datetime").get())
    "implementation"(libs.findLibrary("retrofit-serialization-json").get())
    "implementation"(libs.findLibrary("kotlinx-coroutines-android").get())
}

fun DependencyHandlerScope.monitorProject(libs: VersionCatalog) {
    "debugImplementation"(libs.findBundle("leakcanary-bundle").get())
    "debugCompileOnly"(libs.findLibrary("metrics-performance").get())
    "releaseImplementation"(libs.findLibrary("leakcanary-android-release").get())
    "releaseImplementation"(libs.findLibrary("leakcanary-android-release-watcher").get())
}