import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.30"
}

version = "unspecified"

repositories {
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/spekframework/spek-dev")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation(project(":Exarch"))

    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.3.20")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.1.0-alpha.0.5+1e19e95")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.1.0-alpha.0.5+1e19e95")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}