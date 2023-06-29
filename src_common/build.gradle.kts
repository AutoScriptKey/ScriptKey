plugins {
    id("java"); id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

//Copy over basic app properties, such as version number. So we don't need to change it in multiple files.
tasks.register<Copy>(name = "copyProperties") {
    from(layout.projectDirectory.file("../app.cfg"))
    into(layout.projectDirectory.dir("src/main/resources"))
}

tasks.getByName("processResources") {
    dependsOn("copyProperties")
}