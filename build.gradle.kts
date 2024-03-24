import generator.kindaMain

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    `java-library`
    `maven-publish`
}

group = "org.ldemetrios"
version = "0.1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(kotlin("reflect"))
    implementation("org.ldemetrios:common-utils:1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

val datamodel = File("${project.rootDir}/datamodel").readText()

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
    this.repositories {
        mavenLocal()
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("generateModel") {
    group = "build"
    description = "Generates model classes from datamodel"
    doLast {
        kindaMain(
            datamodelFile = "$rootDir/datamodel",
            prefix = "T",
            commonInterfaceName = "TValue",
            location = "$rootDir/src/main/kotlin/org/ldemetrios/typst4k/orm",
            packageName = "org.ldemetrios.typst4k.orm",
        )
    }
}

kotlin {
    jvmToolchain(17)
}
