import module.plugin.build.monitorProject
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidMonitorConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {

            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                monitorProject(libs)
            }
        }
    }
}