package com.test.schedule.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Clock;
import java.time.LocalDateTime;

@Slf4j
public class SecondScheduler {

    private final Clock clock;

    public SecondScheduler(Clock clock) {
        this.clock = clock;
    }

    @Scheduled(cron = "* 30 * * * MON-FRI", zone = "Asia/Seoul")
    public void secondScheduleJob() {
        log.info("-- USE @Bean at Configuration --");
        log.info("This is scheduled - Run every 30 minutes on weekdays ({})", LocalDateTime.now(clock));
    }
}
