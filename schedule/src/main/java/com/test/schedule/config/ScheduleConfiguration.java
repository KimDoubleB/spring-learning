package com.test.schedule.config;

import com.test.schedule.scheduler.SecondScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Asia/Seoul"));
    }

    @Bean
    public SecondScheduler secondScheduler(Clock clock) {
        return new SecondScheduler(clock);
    }
}
