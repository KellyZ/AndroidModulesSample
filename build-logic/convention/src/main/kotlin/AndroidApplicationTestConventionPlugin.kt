import com.android.build.api.dsl.TestExtension
import module.plugin.build.commonOptions
import module.plugin.build.configureAndroidTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationTestConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.test")
                apply("org.jetbrains.kotlin.android")
            }

            val extension = extensions.getByType<TestExtension>()
            extension.apply {
                compileSdk = 33

                defaultConfig {
                    minSdk = 24

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                commonOptions()

            }
            configureAndroidTest(extension)
        }
    }
}