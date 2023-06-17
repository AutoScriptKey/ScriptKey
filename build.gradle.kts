plugins {
    id("java"); id("application"); id("idea")
}

var name = "ScriptKey"
var fullname = "AutoScriptKey"
version = "PRE-ALPHA"

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
}

tasks.test {
    useJUnitPlatform()
}
