plugins {
    id("java")
}

group = "kr.shihyeon.diamondchecker"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    toolchain.vendor = JvmVendorSpec.ADOPTIUM
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

tasks {

    test {
        useJUnitPlatform()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(file("C:\\Work\\dev-mc-server\\1.20.4\\plugins"))
    }
}