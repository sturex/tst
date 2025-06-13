plugins {
	java
	id("org.springframework.boot") version "3.1.7"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
	withSourcesJar()
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.hibernate.validator:hibernate-validator")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.testcontainers:testcontainers:1.19.7")
	testImplementation("org.testcontainers:junit-jupiter:1.19.7")
	testImplementation("org.testcontainers:postgresql:1.19.7")
	implementation("org.liquibase:liquibase-core")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks {
	withType<Tar> {
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
	}
	withType<Zip> {
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
	}
	bootJar {
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
		archiveFileName.set("app.jar")
	}
	jar {
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
		enabled = false
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks.bootJar {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
	archiveFileName.set("app.jar")
}
