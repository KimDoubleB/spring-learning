# Enable annotation

여러 Spring 외부 모듈을 사용하다보면 `@Enable ~` 어노테이션을 활용해 외무 모듈 기능을 활성화하는 경우가 있다.

이들이 어떻게 이루어지고, 어떻게 사용되는지 알아보자.

- `@Import`를 통해 Configuration class를 등록하여 활성화 시키는 방법
  - `com.bb.app.importConfig` 패키지 참조
- `@Import`를 통해 ImportSelector를 구현한 class를 등록하여 활성화 시키는 방법
  - `com.bb.app.importselector` 패키지 참조
- `@Import`를 통해 ImportBeanDefinitionRegistrar를 구현한 class를 등록하여 활성화 시키는 방법
  - `com.bb.app.importBeanDefinitionRegistrar` 패키지 참조