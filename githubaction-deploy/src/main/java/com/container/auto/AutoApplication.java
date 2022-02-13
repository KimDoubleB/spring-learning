package com.container.auto;

import com.container.auto.profile.ProfileProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AutoApplication implements CommandLineRunner {

    private final ProfileProperty profileProperty;

    public AutoApplication(ProfileProperty profileProperty) {
        this.profileProperty = profileProperty;
    }

    public static void main(String[] args) {
        SpringApplication.run(AutoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Current profile : {}", profileProperty.profile());
    }

}
