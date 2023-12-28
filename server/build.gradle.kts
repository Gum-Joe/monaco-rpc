plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    // Used for RPC
    id("com.github.rnett.krosstalk") version "1.4.0"
    application
}

group = "com.kishan.monaocrpc"
version = "1.0.0"
application {
    mainClass.set("com.kishan.monaocrpc.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
    implementation("com.github.rnett.krosstalk:krosstalk-server:1.4.0")
}