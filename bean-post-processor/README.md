# BeanPostProcessor 테스트


## `BBComponent`
- `BBComponent` 클래스 타입에 대해 BeanPostProcessor가 동작하게 만들어서 `BasicAdvice`가 동작하는 Proxy 객체를 Bean으로 등록해 활용.
- 모든 메서드에 대해서 advice가 동작하도록 구현.

```
2022-09-09 21:18:32.350  INFO 86898 --- [           main] c.example.beanpostprocessor.BBComponent  : [BBComponent] POST CONSTRUCT !
2022-09-09 21:18:33.320  INFO 86898 --- [           main] c.e.b.special.advice.BasicAdvice         : [Basic Advice] 실행
2022-09-09 21:18:33.325  INFO 86898 --- [           main] c.example.beanpostprocessor.BBComponent  : BB Component RUN method
2022-09-09 21:18:33.325  INFO 86898 --- [           main] c.e.b.special.advice.BasicAdvice         : [Basic Advice] 종료
2022-09-09 21:18:33.378  INFO 86898 --- [ionShutdownHook] c.example.beanpostprocessor.BBComponent  : [BBComponent] PRE DESTROY !
```

## `BB2Component`
- `@Special` annotation이 붙은 메서드가 있는 클래스에 대해 BeanPostProcessor가 동작하게 만들어서 `SpecialAdvice`가 동작하는 Proxy 객체를 Bean으로 등록해 활용.
- `@Special` annotation이 붙은 메서드에 대해서만 advice가 동작하도록 구현.

```
2022-09-09 21:17:07.690  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : [BB2Component] POST CONSTRUCT !
2022-09-09 21:17:08.654  INFO 86878 --- [           main] c.e.b.special.advice.SpecialAdvice       : [Special Advice] 실행
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : Special annotation 메서드 실행
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.b.special.advice.SpecialAdvice       : [Special Advice] 종료
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : Special annotation 없는 메서드
2022-09-09 21:17:08.671  INFO 86878 --- [ionShutdownHook] c.e.beanpostprocessor.BB2Component       : [BB2Component] PRE DESTROY !
```

