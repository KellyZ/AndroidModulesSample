package module.plugin.build

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.TestExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidTest(
    commonExtension: CommonExtension<*, *, *, *>,
) {

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures {
            buildConfig = true
        }
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
        commonOptions()
    }

    dependencies{
        testProject(libs, commonExtension is TestExtension)
        testComposeProject(libs, commonExtension is TestExtension)
    }


}