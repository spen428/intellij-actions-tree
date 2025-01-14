import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
  id("java")
  id("org.jetbrains.intellij").version("1.13.2")
  id("org.jetbrains.kotlin.jvm").version("1.8.10")
  id("com.diffplug.spotless").version("6.17.0")
}

group = "com.gitlab.lae.intellij.actions.tree"
version = "0.6.1-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.mockito:mockito-core:3.11.2")
  testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks.withType<Test> {
  testLogging {
    exceptionFormat = TestExceptionFormat.FULL
  }
}

intellij {

  // See idea-version in plugin.xml
  version.set("2022.3.2")

  // If true this sets until-build to same major version which is not good
  updateSinceUntilBuild.set(false)
}

spotless {
  kotlin {
    ktfmt()
  }
}
