package com.test.log.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogUserScheduler {
    private static final Logger log = LoggerFactory.getLogger(LogUserScheduler.class);

    @Scheduled(fixedRate = 5000)
    public void makeDebugLog() {
        log.debug("This is\na \"USER\" debug\nlog");
    }

}
