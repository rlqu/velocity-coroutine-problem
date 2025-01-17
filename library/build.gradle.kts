plugins {
    kotlin("jvm")
}

group = "de.jannik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.kotlin.coroutines)
}

tasks.test {
    useJUnitPlatform()
}