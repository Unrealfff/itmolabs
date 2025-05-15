plugins {
    id("java")
}

group = "org.unrealfff"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.12.1")

}

tasks.test {
    useJUnitPlatform()
}

val jar by tasks.getting(Jar::class) {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "org.unrealfff.Main"
    }

    from(configurations
        .runtimeClasspath
        .get()
        // .get() // uncomment this on Gradle 6+
        // .files
        .map { if (it.isDirectory) it else zipTree(it) })
}