plugins {
    kotlin("jvm") version "1.9.22"
}

group = "likco.flyway.cassandra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.zhicwu:cassandra-jdbc-driver:0.6.4")
    implementation("org.apache-extras.cassandra-jdbc:cassandra-jdbc:1.2.1")
    implementation("com.datastax.oss:java-driver-core:4.17.0")
    implementation(files("libs/cassandrajdbc2.3.3.jar"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.flywaydb:flyway-core:10.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from ({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

kotlin {
    jvmToolchain(16)
}