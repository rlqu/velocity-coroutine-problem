plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
    kotlin("kapt")
}

group = "de.jannik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly(project(":library"))

    compileOnly(libs.kotlin.jvm)
    compileOnly(libs.kotlin.coroutines)
}

tasks.shadowJar {
    archiveFileName = "test-depending-velocity.jar"
}

tasks.test {
    useJUnitPlatform()
}