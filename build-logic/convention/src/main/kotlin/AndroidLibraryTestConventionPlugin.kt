import com.android.build.api.dsl.LibraryExtension
import module.plugin.build.configureAndroidTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryTestConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidTest(extension)
        }
    }
}