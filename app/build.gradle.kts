plugins {
    id("module.plugin.android.application")
    id("module.plugin.android.jetpack")
    id("module.plugin.android.compose")
    id("module.plugin.android.hilt")
    id("module.plugin.matrix")
    id("com.didiglobal.booster")
}

configurations.all {
    // check for updates every build
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}

android {
    namespace = "com.panda.template"

    defaultConfig {
        applicationId = "com.panda.template"
        versionCode =  1
        versionName =  "1.0"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
            // Enables code shrinking, obfuscation, and optimization for only your project's release build type.
            isShrinkResources = true
            // Enables resource shrinking, which is performed by the Android Gradle plugin.
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("benchmark") {
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }

    applicationVariants.all {
        compileConfiguration.exclude(group = "com.google.code.findbugs", module = "jsr305")
        runtimeConfiguration.exclude(group = "com.google.code.findbugs", module = "jsr305")
        true
    }
}

dependencies {
    implementation(project(":arch:DataLayer:api"))
    implementation(project(":arch:DataLayer:data"))
    implementation(project(":arch:DataLayer:database"))
    implementation(project(":arch:DataLayer:fake"))

    implementation(project(":arch:NetworkLayer:api"))
    implementation(project(":arch:NetworkLayer:fake"))

    implementation(project(":feature:foryou"))
    implementation(project(":feature:topic"))
    implementation(project(":feature:plant"))
    implementation(project(":core:monitor"))
    implementation(project(":core:common"))

    implementation("androidx.profileinstaller:profileinstaller:1.3.0-beta01")

}