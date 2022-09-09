package com.example.beanpostprocessor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanPostProcessorApplicationTest {

    @Autowired
    BBComponent bbComponent;

    @Autowired
    BB2Component bb2Component;

    /*
2022-09-09 21:18:32.333  INFO 86898 --- [           main] c.e.beanpostprocessor.BB2Component       : [BB2Component] POST CONSTRUCT !
2022-09-09 21:18:32.350  INFO 86898 --- [           main] c.example.beanpostprocessor.BBComponent  : [BBComponent] POST CONSTRUCT !
2022-09-09 21:18:33.320  INFO 86898 --- [           main] c.e.b.special.advice.BasicAdvice         : [Basic Advice] 실행
2022-09-09 21:18:33.325  INFO 86898 --- [           main] c.example.beanpostprocessor.BBComponent  : BB Component RUN method
2022-09-09 21:18:33.325  INFO 86898 --- [           main] c.e.b.special.advice.BasicAdvice         : [Basic Advice] 종료
2022-09-09 21:18:33.378  INFO 86898 --- [ionShutdownHook] c.example.beanpostprocessor.BBComponent  : [BBComponent] PRE DESTROY !
2022-09-09 21:18:33.378  INFO 86898 --- [ionShutdownHook] c.e.beanpostprocessor.BB2Component       : [BB2Component] PRE DESTROY !
     */
    @DisplayName("BBComponent class에 대해 빈 후처리기를 적용한다")
    @Test
    void bbComponentTest() {
        var result = bbComponent.run("bbComponentTest");
        assertThat(result).isEqualTo("[BB Component] : bbComponentTest");
    }

    /*
2022-09-09 21:17:07.690  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : [BB2Component] POST CONSTRUCT !
2022-09-09 21:17:07.711  INFO 86878 --- [           main] c.example.beanpostprocessor.BBComponent  : [BBComponent] POST CONSTRUCT !
2022-09-09 21:17:08.654  INFO 86878 --- [           main] c.e.b.special.advice.SpecialAdvice       : [Special Advice] 실행
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : Special annotation 메서드 실행
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.b.special.advice.SpecialAdvice       : [Special Advice] 종료
2022-09-09 21:17:08.659  INFO 86878 --- [           main] c.e.beanpostprocessor.BB2Component       : Special annotation 없는 메서드
2022-09-09 21:17:08.671  INFO 86878 --- [ionShutdownHook] c.example.beanpostprocessor.BBComponent  : [BBComponent] PRE DESTROY !
2022-09-09 21:17:08.671  INFO 86878 --- [ionShutdownHook] c.e.beanpostprocessor.BB2Component       : [BB2Component] PRE DESTROY !
     */
    @DisplayName("Special Annotation이 붙은 메서드만 Advice를 적용한다")
    @Test
    void bb2ComponentTest() {
        var specialResult = bb2Component.run("with Special annotation");
        var withoutSpecialAnnotation = bb2Component.run2("without Special annotation");

        assertThat(specialResult).isEqualTo("[BB2 Component - Special Annotation] : with Special annotation");
        assertThat(withoutSpecialAnnotation).isEqualTo("[BB2 Component] : without Special annotation");
    }

}