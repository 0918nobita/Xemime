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
}

application {
    mainClassName = "vision.kodai.xemime.MainKt"
}
