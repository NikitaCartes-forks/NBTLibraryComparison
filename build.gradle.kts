plugins {
    id("java")
    id ("com.github.johnrengelman.shadow") version "7.1.2"
    id ("fabric-loom") version "1.5-SNAPSHOT"
}

group = "de.bluecolored.nbtlibtest"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://maven.izzel.io/releases")
    maven("https://maven.syntaxerror.at")
    maven("https://repo.viaversion.com")
    maven("https://repo.inventivetalent.org/content/groups/public/")
}

dependencies {
    implementation (group = "net.kyori", name = "adventure-nbt", version = "4.11.0")
    implementation (group = "com.github.BlueMap-Minecraft", name = "BlueNBT", version = "v1.3.0")
    implementation (group = "com.github.Querz", name = "NBT", version = "6.1")
    implementation (group = "org.popcraft", name = "chunky-nbt", version = "1.3.76")
    implementation (group = "com.github.GeyserMC", name = "OpenNBT", version = "1.5")
    implementation (files("libs/JNBT_1.5.jar"))
    implementation (group = "dev.dewy", name = "nbt", version = "1.5.1")
    implementation (group = "io.izzel", name = "nbt", version = "1.1.0")
    implementation (group = "com.github.AllayMC", name = "NBT", version = "3.0.8")
    implementation (group = "com.github.piegamesde", name = "nbt", version = "3.0.1")
    implementation (group = "dev.cerus", name = "simple-nbt", version = "1.1.8")


    // Minecraft dependencies
    minecraft ("com.mojang:minecraft:24w04a")
    mappings (loom.officialMojangMappings())

    compileOnly ("org.projectlombok:lombok:1.18.28")

    annotationProcessor ("org.projectlombok:lombok:1.18.28")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testCompileOnly ("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.28")
}

tasks.jar {
    manifest {
        attributes (
            "Main-Class" to "de.bluecolored.nbtlibtest.PerformanceTest"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}