plugins {
    application
    kotlin("jvm") version "1.3.72"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

group = "vision.kodai"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation("io.arrow-kt:arrow-core:0.10.5")
    implementation("io.arrow-kt:arrow-mtl:0.10.5")
    implementation("io.arrow-kt:arrow-syntax:0.10.5")
}

application {
    mainClassName = "vision.kodai.xemime.MainKt"
}

ktlint {
    verbose.set(true)
}
