import java.util.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
	java
	kotlin("jvm") version "1.9.22"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}
var mapStructVersion = "1.6.3"
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}


tasks.named("build").configure {
	doLast {
		val trigger = file ("src/main/resources/trigger.txt")
		if(!trigger.exists()){
			trigger.createNewFile()
		}
		trigger.writeText(Date().time.toString())
	}
}

tasks.register("generateFlywayMigrationsFile") {

	description = "Generate Flyway Migrations"
	group = "Flyway"

	doLast {
		val migrationsDir = file("src/main/resources/db/migration")
		if (!migrationsDir.exists()) {
			migrationsDir.mkdirs();
		}

		val migrationNameFromConsole =
			project.findProperty("migrationName") as String? ?: throw IllegalArgumentException("Vocẽ deve informar um nome para a migração usando o parametro -PmigrationName")

		val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
		val migrationsName = "V${timestamp}__${migrationNameFromConsole}.sql"

		val migrationFile = file("${migrationsDir.path}/$migrationsName")
		migrationFile.writeText("-- %migrationName generated in ${migrationsDir.path}")


		logger.lifecycle("Migration file created: ${migrationFile.path}")

	}
}
