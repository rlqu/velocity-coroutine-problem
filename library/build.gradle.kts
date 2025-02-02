plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
}

group = "de.jannik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.kotlin.coroutines)
}

tasks.shadowJar {
    archiveFileName = "test-library.jar"
}

tasks.test {
    useJUnitPlatform()
}