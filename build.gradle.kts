import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("io.qameta.allure") version "2.9.6"

}

group = "wearetech.start2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.19.0"
var junitVersion = "5.8.2"
var slf4jVersion = "1.7.32"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation( "io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation( "com.codeborne:selenide:5.25.0")
    testImplementation( "io.qameta.allure:allure-selenide:$allureVersion")
    testImplementation( "io.appium:java-client:7.6.0")
    testImplementation( "org.aeonbits.owner:owner:1.0.12")
    testImplementation( "io.rest-assured:rest-assured:4.4.0")
    testImplementation( "org.junit.jupiter:junit-jupiter-api:$junitVersion")

    testRuntimeOnly(
            "org.slf4j:slf4j-simple:$slf4jVersion",
            "org.junit.jupiter:junit-jupiter-engine:$junitVersion"
    )
}

tasks.test {
    useJUnitPlatform()
    testLogging{
        showExceptions = true
//        exceptionFormat = TestExceptionFormat.FULL
        exceptionFormat = TestExceptionFormat.SHORT
//        showStackTraces = true
//        showCauses = true
//        showStandardStreams = true
        events = setOf(
                TestLogEvent.PASSED,
                TestLogEvent.SKIPPED,
                TestLogEvent.FAILED,
                TestLogEvent.STANDARD_OUT,
                TestLogEvent.STANDARD_ERROR
        )
    }
}