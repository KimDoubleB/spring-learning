# Client-side load balancing using Spring Cloud LoadBalancer

Spring Cloud LoadBalancer를 이용한 Client side load balancing 예제입니다.

## 개요

해당 프로젝트는 멀티모듈로 2개의 모듈로 나누어져 있습니다.

- Server
  - Server 모듈은 단순히 요청을 받아 로그를 찍어주는 역할을 하는 서버입니다.

- Client
  - Client에서 여러 Server를 load balancing 하여 HTTP 요청을 송신합니다.
  - `ServiceInstanceListSupplier`를 구현(implement)하여 Target 서버들을 지정하여 로드밸런싱 하도록 작성하였습니다.
    - 요청할 Server 목록은 `application.yml`를 통해 프로퍼티로 받아올 수 있도록 작성했습니다.


## 동작


### 서버 시작

```bash
# project root
./gradlew bootJar

# run server 1
java -jar -DSERVER_PORT=8081 server/build/libs/server-0.0.1-SNAPSHOT.jar

# run server 2
java -jar -DSERVER_PORT=8082 server/build/libs/server-0.0.1-SNAPSHOT.jar

# run server 3
java -jar -DSERVER_PORT=8083 server/build/libs/server-0.0.1-SNAPSHOT.jar

# run client
java -jar client/build/libs/client-0.0.1-SNAPSHOT.jar

```

### 요청
```bash
# call client endpoint
curl -X GET localhost:8080/api/v1/server
```

### 영상

![demo-video](https://user-images.githubusercontent.com/37873745/150817929-e840125d-0aa2-4f95-bd3a-aa189c26a439.gif)
