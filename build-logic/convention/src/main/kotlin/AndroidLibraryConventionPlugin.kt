import com.android.build.gradle.LibraryExtension
import module.plugin.build.configureKotlinAndroid
import module.plugin.build.kotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("kotlinx-serialization")
            }

            kotlinAndroid {
                jvmToolchain(11)
            }

            val extension = extensions.getByType<LibraryExtension>()

            extension.apply {
                buildFeatures {
                    dataBinding = true
                }
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 33
            }

        }
    }
}