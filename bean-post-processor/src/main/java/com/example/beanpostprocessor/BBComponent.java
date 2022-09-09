package com.example.beanpostprocessor;

import com.example.beanpostprocessor.special.Special;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class BBComponent {

    @PostConstruct
    private void postConstruct() {
        log.info("[BBComponent] POST CONSTRUCT !");
    }

    @PreDestroy
    private void preDestroy() {
        log.info("[BBComponent] PRE DESTROY !");
    }

    public String run(String word) {
        log.info("BB Component RUN method");
        return "[BB Component] : %s".formatted(word);
    }

//    @Special
    public String specialAnnotation() {
        log.info("BB Component Special annotation method");
        return "[BB Component] Special annotation method";
    }

}
