# Test

- opentelemetry instrumentation 2.13.1

<br/>

## When Java 11, Spring boot 2.7.18

Failed to resolve runtime dependencies

- `issue/java11` branch
- `./gradlew clean dependencies --configuration runtimeClasspath`

```text
\--- io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter -> 2.13.1
     +--- io.opentelemetry.instrumentation:opentelemetry-spring-boot-autoconfigure:2.13.1
     |    +--- ...
     |    +--- io.opentelemetry.instrumentation:opentelemetry-runtime-telemetry-java17:2.13.1-alpha FAILED
```

<br/>

## When Java 17, Spring boot 3.4.3

Successfully resolved runtime dependencies

- `issue/java17` branch
- `./gradlew clean dependencies --configuration runtimeClasspath`

```text
\--- io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter -> 2.13.1
     +--- io.opentelemetry.instrumentation:opentelemetry-spring-boot-autoconfigure:2.13.1
     |    +--- ...
     |    +--- io.opentelemetry.instrumentation:opentelemetry-runtime-telemetry-java17:2.13.1-alpha
     |    |    +--- io.opentelemetry.instrumentation:opentelemetry-runtime-telemetry-java8:2.13.1-alpha (*)
     |    |    +--- io.opentelemetry.instrumentation:opentelemetry-instrumentation-api:2.13.1 (*)
```
