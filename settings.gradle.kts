pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/") {
			name = "Fabric"
		}

		mavenCentral()
		gradlePluginPortal()
	}

	plugins {
		id("net.fabricmc.fabric-loom-remap") version providers.gradleProperty("loom_version")
        id("com.diffplug.spotless") version providers.gradleProperty("spotless_version")
	}
}

rootProject.name = "weri"
