import com.android.build.api.dsl.ApplicationExtension
import module.plugin.build.configureKotlinAndroid
import module.plugin.build.kotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidKotlinApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlinx-serialization")
            }

            kotlinAndroid {
                jvmToolchain(11)
            }

            val extension = extensions.getByType<ApplicationExtension>()

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