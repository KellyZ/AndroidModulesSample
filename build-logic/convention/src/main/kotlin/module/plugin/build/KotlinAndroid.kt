package module.plugin.build

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 33

        defaultConfig {
            minSdk = 24
            vectorDrawables {
                useSupportLibrary = true
            }
            ndk {
                abiFilters+= listOf("armeabi-v7a", "arm64-v8a")
            }
        }

        sourceSets {
            getByName("main") {
                jniLibs.srcDirs("libs")
            }
        }

        commonOptions()

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
            jniLibs.pickFirsts.add("lib/x86/libc++_shared.so")
            jniLibs.pickFirsts.add("lib/x86_64/libc++_shared.so")
            jniLibs.pickFirsts.add("lib/armeabi-v7a/libc++_shared.so")
            jniLibs.pickFirsts.add("lib/arm64-v8a/libc++_shared.so")
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        kotlinProject(libs)
        commonUtilProject(libs)
    }
}