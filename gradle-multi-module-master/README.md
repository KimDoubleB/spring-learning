# Gradle settings in multi projects
## buildscript
buildscript는 gradle로 task 를 수행할 때에 사용되는 설정
```
buildscript {
    ext {
        springBootVersion = '2.1.3.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}
```

- 종종 스크립트를 통해 바로 외부 라이브러리의 특정 메소드를 사용해야 하는 경우가 있다. 그럴 경우 buildscript를 작성해 사용
- 여기서 spring-boot-gradle-plugin classpath로 버전을 설정해두었기에 뒤 dependencies 에서 spring boot dependencies들은 버전을 명시하지 않아도 여기서 명시한 버전으로 설정된다.
  - 만약 dependencies에서 버전을 명시할 경우, buildscript에서 정의한 버전은 무시된다. (조심해야한다)

## ext
gradle에서 사용하는 전역변수 설정

## plugin
- spring boot dependency 관리 및 application 패키징 도와주는 도구
- 실행가능한 jar, war 파일들로 패키징을 해주기도 하고, spring-boot-dependencies를 통해서 의존성 관리 기능을 제공하기도 한다.

1. each plugin
```
apply plugin: 'java'
apply plugin: 'org.springframework.boot'
```
2. plugins
```
plugins {
    id 'java'
    id 'org.springframework.boot'
}
```

## Version, Encoding
java version, compile version, encoding 정의
```
group 'com.sungjun'
version '1.0-SNAPSHOT'
sourceCompatibility = 1.8
compileJava.options.encoding = 'UTF-8'
```

## Repositories
각종 의존성(dependency)들을 어떤 원격 저장소에서 받을지 설정
- 기본적으로 mavenCentral을 사용하지만, 최근에는 라이브러리 업로드 난이도 때문에 jcenter도 많이 사용한다.
```
repositories {
    mavenCentral()
    jcenter()
}
```

## projects
### allprojects
Root project를 비롯해 모든 project에 필요한 설정 작성
- allprojects로 설정이 들어가게 되면, 모든 project들은 위 속성을 모두 가지게 된다
```
allprojects {
    group 'com.sungjun'
    version '1.0-SNAPSHOT'
}
```

### subprojects
allprojects와 비슷하지만, Root project는 설정 적용에서 제외된다는 차이 존재
- 현업에서 Root project는 project에 대한 이름/패키징을 맡기고, 하위에 서비스마다의 sub projects를 이룰 때 사용
```
subprojects {
    apply plugin: 'java'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'

    sourceCompatibility = 1.8

    repositories {
        mavenCentral()
    }

    dependencies {
        testCompile group: 'junit', name: 'junit', version: '4.12'
    }
}
```

### project
Root project에서 하위 project 연결
```
project(:service-project-name) {
    ...
}

// OR

project(:service-project-name)

```

## dependencies
의존성 모듈(라이브러리) 추가
```
dependencies {
    compile('org.springframework.retry:spring-retry:1.2.2.RELEASE')  // spring cloud requires spring-retry for auto-retry
    compile('org.springframework.cloud:spring-cloud-starter-openfeign')  // To use Feign
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client') // 3. To use Eureka client
    compile('org.springframework.cloud:spring-cloud-starter-netflix-ribbon')  // 2. To use ribbon
    compile('org.springframework.cloud:spring-cloud-starter-netflix-hystrix') // 1. To use spring-cloud-hystrix
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

```

과거코드를 보면 compile이 사용되고, 최근 코드를 보면 implementation이 사용된다.
- 무슨 차이일까?: https://hack-jam.tistory.com/13
![](https://user-images.githubusercontent.com/37873745/109424705-11274980-7a28-11eb-8287-c1466dc2f78f.png)
- 결론: `compile`은 deprecated 되었다. `implementation`이 더 빠르고 좋으므로 `implementation`을 사용하자
```
dependencies {
	implementation project(':api')
	implementation project(':util')
	implementation('org.springframework.boot:spring-boot-starter-actuator')
	implementation('org.springframework.boot:spring-boot-starter-webflux')
	implementation('org.springframework.boot:spring-boot-starter-security')
	implementation('org.springframework.security:spring-security-oauth2-resource-server')
	implementation('org.springframework.security:spring-security-oauth2-jose')

	testImplementation('org.springframework.boot:spring-boot-starter-test')
	testImplementation('org.springframework.cloud:spring-cloud-stream-test-support')
	testImplementation('io.projectreactor:reactor-test')
}
```

## settings.gradle
multi project gradle 사용할 시, 작성해야 한다.
```
rootProject.name = 'sample-multi-module'
include 'sample-api'
include 'sample-admin'
include 'sample-common'
```

# Reference
https://gwonsungjun.github.io/articles/2019-04/gradle_multi_module
https://jojoldu.tistory.com/123