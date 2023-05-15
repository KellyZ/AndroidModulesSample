import com.android.build.api.dsl.ApplicationExtension
import com.tencent.matrix.plugin.extension.MatrixExtension
import com.tencent.matrix.trace.extension.MatrixTraceExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate

class AndroidMatrixConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
//            println(rootProject.extra.properties["debug"])
            val debug: Boolean = rootProject.extra.properties["debug"] as Boolean
            if (debug) {
                with(pluginManager) {
                    apply("com.tencent.matrix-plugin")
                }

                val extension = extensions.getByType<ApplicationExtension>()
                extension.apply {
                    packagingOptions {
                        jniLibs.pickFirsts.add("lib/x86/libc++_shared.so")
                        jniLibs.pickFirsts.add("lib/x86_64/libc++_shared.so")
                        jniLibs.pickFirsts.add("lib/armeabi-v7a/libc++_shared.so")
                        jniLibs.pickFirsts.add("lib/arm64-v8a/libc++_shared.so")
                    }

                    matrix {
                        logLevel = "D"
                        trace {
                            isEnable = true    //if you don't want to use trace canary, set false
                            baseMethodMapFile = "${project.projectDir}/matrixTrace/methodMapping.txt"
                            blackListFile = "${project.projectDir}/matrixTrace/blackMethodList.txt"
                        }
                    }
                }
            }
        }
    }
}

fun Project.matrix(block: MatrixExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("matrix", block)
}

fun MatrixExtension.trace(block: MatrixTraceExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("trace", block)
}