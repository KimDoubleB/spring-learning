# Getting configurations using ComponentScan annotation

`@ComponentScan`을 통해 Configuration 클래스들을 등록할 수 있다.
- `@SpringBootApplication`은 `@ComponentScan`을 포함하는 어노테이션이기에 기본적인 구조(spring initializer를 사용한 상황)에선 Component scan을 사용한 방식으로 Configuration을 등록하게 된다.