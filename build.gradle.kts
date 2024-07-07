plugins {
    java
    kotlin("jvm") version "1.9.23"
}

group = "com.calmwolfs.valorantmodelapi"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.code.gson:gson:2.11.0")
}

kotlin {
    jvmToolchain(17)
}