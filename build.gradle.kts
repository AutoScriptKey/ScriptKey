import java.util.Properties
import java.io.FileReader

plugins {
    id("java"); id("application"); id("idea")
}

version = getFromProperties("autoscriptkey.version")

var name = "ScriptKey"
var fullName = "AutoScriptKey"

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

    // Include common source project, which contains reusable code shared across projects and subprojects.
    implementation(project(":src_common"))

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * Basic function to get the value of a property defined in /app.cfg.
 */
fun getFromProperties(name: String): String {
    val props = Properties()
    props.load(FileReader(layout.projectDirectory.file("app.cfg").asFile))
    return props.getProperty(name)
}
