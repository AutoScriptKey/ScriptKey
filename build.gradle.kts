import java.util.*

plugins {
    id("java"); id("application"); id("idea")
}

var name = "ScriptKey"
var fullname = "Auto$name"
version = "PRE-ALPHA"

group = "org.${name.lowercase(Locale.getDefault())}"

application {
    mainClass.set("$group.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}