package com.test.schedule.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Slf4j
@Component
public class FirstScheduler {

    private final Clock clock;

    public FirstScheduler(Clock clock) {
        this.clock = clock;
    }

    @Scheduled(cron = "*/15 * * * * *", zone = "Asia/Seoul")
    public void firstScheduleJob() {
        log.info("-- USE @Component --");
        log.info("This is scheduled - Run every 15 seconds ({})", LocalDateTime.now(clock));
    }

    @Scheduled(cron = "${schedule.cron}", zone = "Asia/Seoul")
    public void firstScheduleSecondJob() {
        log.info("-- USE @Component --");
        log.info("This is scheduled - Run every 30 seconds ({})", LocalDateTime.now(clock));
    }


}
