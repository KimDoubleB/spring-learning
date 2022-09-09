package com.example.beanpostprocessor;

import com.example.beanpostprocessor.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class BB2Component {

    @PostConstruct
    private void postConstruct() {
        log.info("[BB2Component] POST CONSTRUCT !");
    }

    @PreDestroy
    private void preDestroy() {
        log.info("[BB2Component] PRE DESTROY !");
    }

    @Special
    public String run(String word) {
        log.info("Special annotation 메서드 실행");
        return "[BB2 Component - Special Annotation] : %s".formatted(word);
    }

    public String run2(String word) {
        log.info("Special annotation 없는 메서드");
        return "[BB2 Component] : %s".formatted(word);
    }

}
