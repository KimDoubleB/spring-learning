/metrics 가면 제공되는 메트릭 정보 이름 확인할 수 있음

https://docs.spring.io/spring-boot/redirect.html?page=actuator#actuator.metrics.supported

---

MetricsAutoConfiguration
MetricsEndpointAutoConfiguration

-> MetricsEndpoint


MeterBinder
- CustomMetric

---

?tag=key:value
- 특정 태그의 값만 조회하고 싶은 경우

tag를 통해 같은 counter 인데도 특징으로 분리 가능
- 일반 메트릭 조회에서는 그냥 단순 합을 표현하고
- tag로 상세조회가 가능함

---

Counter의 tag는 id와 같음
- 즉, name이 같더라도 tag가 다르면 사실 객체 상으론 다른 Counter로 취급됨 (인스턴스가 다름)
- MetricId가 다르게 설정됨
    - Metric Id 생성에는 name과 tag 둘 다 사용되기 때문

----

Gauge는 Counter와 달리 그 때(요청이 왔을 때)의 값이기 때문에 로직에서 계속 추가하는 코드를 넣을 필요가 없음
- Counter와 달리 어떤 값을 반환할 지만 설정해주면 된다. FunctionCounter처럼 이용한다고 생각하면 됨.

MeterBinder를 통해 하나의 Bean으로 등록하는 편이 제일 깔끔해 보임
- Spring 단에서 MeterBinder Bean들을 다 보아서 한번에 등록처리해주는 Configuration 코드가 있을 것으로 보임

---

Timer, Timed는 생각보다 사용할 사례가 많을듯?
- 시간 관련 측정하고 직접 구현하기 귀찮을 때가 많은데, 손 쉽게 메트릭으로 노출해서 값 확인할 수 있음.

Counted, Timed 같은 AOP를 이용하기 위해선 애플리케이션 Configuration으로 직접 등록해줘야 함.
- 흠... 왜 actautor 같은거에서 자동으로 등록을 안해줬을까? 의문이 들긴 함

---

Functional과 비교가 조금 헷갈리긴 한다.
- Manager 같이 값을 관리하는 객체를 FunctionalCount, FunctionalTimer로 넘겨서 값 변경하게 끔 하는 것으로 이해함
- 그럼 직접 사용하는 측에서는 Functional 이거 빈 호출해서 뭔가 처리해주는 건가? 갑자기 헷갈림ㅎ
- ㄴㄴ 따로 등록안해줘도 Functional interface  자체를 registry에 추가해주는 것이기 때문에 따로 추가는 안해도 되는 것으로 보임. 근데 그 저장되는 값을 관리하는 객체(Manager)를 따로 만들어줘서 관리해줘야 하는 듯? => 이것만 조정하면 알아서 metric에 값이 반영되고? (metric 조회 시 count만 하면 되니 ㅎ)
