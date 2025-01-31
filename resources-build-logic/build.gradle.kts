plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()

    gradlePluginPortal()
}

dependencies {
    api("dev.icerock:mobile-multiplatform:0.13.0")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    api("com.android.tools.build:gradle:7.0.4")
    api("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.15.0")
}
