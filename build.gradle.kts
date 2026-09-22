plugins {
	id("net.fabricmc.fabric-loom-remap")
	`maven-publish`
}

fun String.runCommand(workingDir: File = file("./")): String {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start()

    proc.waitFor(1, TimeUnit.MINUTES)
    return proc.inputStream.bufferedReader().readText().trim()
}

version = providers.gradleProperty("version").get() + "+" + "git rev-parse --short HEAD".runCommand()

repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }

    maven {
        name = "Ladysnake Mods"
        url = uri("https://maven.ladysnake.org/releases")
    }

    maven {
        name = "Datasync"
        url = uri("https://maven.uuid.gg/releases")
    }

    maven {
        name = "MaxHenkel"
        url = uri("https://maven.maxhenkel.de/releases")
    }

    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
    }

    maven {
        name = "FzzyMaven"
        url = uri("https://maven.fzzyhmstrs.me/")
    }

    maven {
        name = "henkelmax.public"
        url = uri("https://maven.maxhenkel.de/repository/public")
    }

    maven {
        name = "WispForest"
        url = uri("https://maven.wispforest.io/releases")
    }
}

loom {
	splitEnvironmentSourceSets()

	mods {
		register("wathe-extended-rus-improved") {
			sourceSet(sourceSets.main.get())
			sourceSet(sourceSets.getByName("client"))
		}
	}

    log4jConfigs.from(file("log4j-dev.xml"))
}

dependencies {
	minecraft("com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}")
    mappings("net.fabricmc:yarn:${providers.gradleProperty("yarn_mappings").get()}:v2")
	modImplementation("net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}")

	modImplementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_api_version").get()}")

    modImplementation("maven.modrinth:wathe-extended:${providers.gradleProperty("wathe_extended_version").get()}")
    modApi("dev.doctor4t:wathe:${providers.gradleProperty("wathe_version").get()}")

    modApi("maven.modrinth:harpymodloader:${providers.gradleProperty("harpymodloader_version").get()}")

    modApi("maven.modrinth:noelles-roles-tmm:${providers.gradleProperty("noelles_roles_version").get()}")
    modApi("maven.modrinth:stupid-express:${providers.gradleProperty("stupid_express_version").get()}")
    modApi("maven.modrinth:starexpress:${providers.gradleProperty("starexpress_version").get()}")
    modApi("maven.modrinth:kinswathe:${providers.gradleProperty("kinswathe_version").get()}")

    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-base:${providers.gradleProperty("cca_version").get()}")
    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-entity:${providers.gradleProperty("cca_version").get()}")
    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-world:${providers.gradleProperty("cca_version").get()}")
    modImplementation("org.ladysnake.cardinal-components-api:cardinal-components-scoreboard:${providers.gradleProperty("cca_version").get()}")
    modImplementation("dev.doctor4t:ratatouille:${providers.gradleProperty("ratatouille_version").get()}")

    modImplementation("dev.isxander:yet-another-config-lib:${providers.gradleProperty("yacl_version").get()}")
    modImplementation("me.fzzyhmstrs:fzzy_config:${providers.gradleProperty("fzzyConfigVersion").get()}")
    modImplementation("io.wispforest:owo-lib:${providers.gradleProperty("owo_version").get()}")

    implementation("de.maxhenkel.voicechat:voicechat-api:${providers.gradleProperty("voicechat_api_version").get()}")
    modRuntimeOnly("de.maxhenkel.voicechat:voicechat-api:${providers.gradleProperty("voicechat_api_version").get()}:fabric-stub")
    modRuntimeOnly("maven.modrinth:simple-voice-chat:fabric-${providers.gradleProperty("voicechat_mod_version").get()}")

    modRuntimeOnly("maven.modrinth:modmenu:${providers.gradleProperty("modmenu_version").get()}")
    modRuntimeOnly("maven.modrinth:carpet:${providers.gradleProperty("carpet_version").get()}")
    modRuntimeOnly("maven.modrinth:playercontrol:${providers.gradleProperty("playercontrol_version").get()}")
}

tasks.processResources {
	val version = version
	inputs.property("version", version)

	filesMatching("fabric.mod.json") {
		expand("version" to version)
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 21
}

java {
	withSourcesJar()

	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
	val projectName = project.name
	inputs.property("projectName", projectName)

	from("LICENSE") {
		rename { "${it}_$projectName" }
	}
}

publishing {
	publications {
		register<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}

	repositories {
	}
}
