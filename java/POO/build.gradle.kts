plugins {
    id("java")
}

group = "org.main"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.27")
    implementation("org.jdatepicker:jdatepicker:1.3.4")
    implementation("com.itextpdf:itextpdf:5.5.13.4")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}