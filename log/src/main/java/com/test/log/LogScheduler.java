package com.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogScheduler {
    private static final Logger log = LoggerFactory.getLogger(LogScheduler.class);

    @Scheduled(fixedRate = 2000)
    public void makeDebugLog() {
        log.debug("This is\na debug\nlog");
    }

    @Scheduled(fixedRate = 5000)
    public void makeInfoLog() {
        log.info("This is\na info\nlog");
    }

    @Scheduled(fixedRate = 8000)
    public void logException() {
        throw new RuntimeException("! Error happened !");
    }

}
