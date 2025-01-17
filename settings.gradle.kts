plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "velocity-kotlin-test"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            repositories {
                maven {
                    name = "github"
                    url = uri("https://maven.pkg.github.com/RevoltixDevelopment/build-tools")
                    credentials(PasswordCredentials::class)
                }
            }
            from("net.revoltix:build-tools:RELEASE-0.6")
        }
    }
}
include("plugin")
include("library")
include("depending-plugin")
