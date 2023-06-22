plugins {
    id("java"); id("application"); id("idea")
}

version = "PRE-ALPHA"

var name = "ScriptKey"
var fullname = "AutoScriptKey"

group = "org.scriptkey"

application {
    mainClass.set("$group.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
}

tasks.test {
    useJUnitPlatform()
}
