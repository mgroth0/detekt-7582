import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

val USE_SNAPSHOT = true
buildscript {
    val USE_SNAPSHOT = true
    repositories {
        if (USE_SNAPSHOT) {
            maven {
                url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            }
        } else {
            mavenLocal {
                url =  uri(File("/Users/matthewgroth/registered").resolve("maven").resolve("controlled"))
            }
        }

    }
    dependencies {
        classpath(
            if (USE_SNAPSHOT)  "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:main-SNAPSHOT"
            else "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:local-snapshot"
        )
    }
}

plugins {
    val ktVersion= "2.0.20"
    kotlin("android") version ktVersion
    id("com.android.application") version "8.5.2"
}
plugins.apply("io.gitlab.arturbosch.detekt")

repositories {
    mavenCentral()
    if (USE_SNAPSHOT) {
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
    else {
        mavenLocal {
            url = uri(File("/Users/matthewgroth/registered").resolve("maven").resolve("controlled"))
        }
    }

    google()
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "org.gradle.samples"
        namespace = "org.gradle.samples"
        minSdk = (21)
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        val jvmVersion = JavaVersion.VERSION_21
        sourceCompatibility = jvmVersion
        targetCompatibility = jvmVersion
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
}


tasks.register("allDetekt") {
    dependsOn(tasks.withType<Detekt>())
}

extensions.configure(DetektExtension::class.java) {
    debug = true
    config.from(file("detekt.yml"))
}
