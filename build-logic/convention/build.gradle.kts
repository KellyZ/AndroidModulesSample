import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//  Let Gradle control the version of `kotlin-dsl` by removing any explicit `kotlin-dsl` version
    `kotlin-dsl`
}

group = "com.module.plugin.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

afterEvaluate {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.matrix.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "module.plugin.android.application"
            implementationClass = "AndroidKotlinApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "module.plugin.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidJetpack") {
            id = "module.plugin.android.jetpack"
            implementationClass = "AndroidJetpackConventionPlugin"
        }
        register("androidAppCompose") {
            id = "module.plugin.android.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibCompose") {
            id = "module.plugin.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "module.plugin.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidDatabase") {
            id = "module.plugin.android.database"
            implementationClass = "AndroidDatabaseConventionPlugin"
        }
        register("androidNetwork") {
            id = "module.plugin.android.network"
            implementationClass = "AndroidNetworkConventionPlugin"
        }
        register("androidTest") {
            id = "module.plugin.android.test"
            implementationClass = "AndroidApplicationTestConventionPlugin"
        }
        register("libraryTest") {
            id = "module.plugin.library.test"
            implementationClass = "AndroidLibraryTestConventionPlugin"
        }
        register("javaKotlinLibrary") {
            id = "module.plugin.javakotlin.library"
            implementationClass = "JavaKotlinLibraryConventionPlugin"
        }
        register("monitor") {
            id = "module.plugin.android.monitor"
            implementationClass = "AndroidMonitorConventionPlugin"
        }
        register("matrix") {
            id = "module.plugin.matrix"
            implementationClass = "AndroidMatrixConventionPlugin"
        }
        register("matrix-deps") {
            id = "module.plugin.matrix.depends"
            implementationClass = "AndroidMatrixDepsConventionPlugin"
        }
    }
}