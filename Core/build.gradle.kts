import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

group = "net.publicmethod"
version = "0.0.1-ALPHA"

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/spekframework/spek-dev")
}



dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0-alpha-2")
    
    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.3.21")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.1.0-alpha.0.5+1e19e95")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.1.0-alpha.0.5+1e19e95")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
