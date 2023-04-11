plugins {
	java
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.platzi"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.springfox:springfox-swagger2:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt:0.9.1")

	runtimeOnly ("org.postgresql:postgresql")
	implementation ("org.mapstruct:mapstruct:1.5.3.Final")
	annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.3.Final")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
