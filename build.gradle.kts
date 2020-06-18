plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "vision.kodai"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
}

application {
    mainClassName = "vision.kodai.xemime.MainKt"
}
