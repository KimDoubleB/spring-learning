plugins {
    java
   id("org.springframework.boot") version "2.7.18"
   id("io.spring.dependency-management") version "1.1.6"
}

group = "com.double"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
       languageVersion = JavaLanguageVersion.of(11)
    }
}

dependencyManagement {
    imports {
        mavenBom("io.opentelemetry.instrumentation:opentelemetry-instrumentation-bom:2.13.1")
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

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // otel
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
