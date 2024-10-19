pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
    }
}

plugins { id("dev.kikugie.stonecutter") version "0.4.4" }

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    shared { versions("1.21", "1.21.1") }
    create(rootProject)
}

rootProject.name = "Template"
