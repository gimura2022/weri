pluginManagement {
	repositories {
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}
		mavenCentral()
		gradlePluginPortal()
	}

	plugins {
		id("net.fabricmc.fabric-loom-remap") version providers.gradleProperty("loom_version")
        id("dev.javafmt.gradle") version providers.gradleProperty("javafmt_version")
	}
}

rootProject.name = "wathe-extended-rus-improved"
