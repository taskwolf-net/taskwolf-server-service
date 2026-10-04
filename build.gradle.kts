plugins {
  id("java")
}

group = "com.dulno"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(platform("org.junit:junit-bom:5.12.0"))
  testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")

  implementation("com.google.inject:guice:7.0.0")

  implementation("com.google.guava:guava:33.4.0-jre")

  implementation("org.projectlombok:lombok:1.18.36")
  annotationProcessor("org.projectlombok:lombok:1.18.36")
  testImplementation("org.projectlombok:lombok:1.18.36")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

  implementation("org.json:json:20250107")
  implementation("commons-io:commons-io:2.18.0")

  implementation("org.java-websocket:Java-WebSocket:1.6.0")
}

tasks.test {
  useJUnitPlatform()
}

tasks.jar {
  manifest.attributes["Main-Class"] = "com.dulno.server.service.ServerServiceApplication"
  val dependencies = configurations
    .runtimeClasspath
    .get()
    .map(::zipTree)
  from(dependencies)
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}