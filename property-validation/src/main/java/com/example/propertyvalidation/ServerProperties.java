package com.example.propertyvalidation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "server")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
@ToString
public class ServerProperties {

    private final String value1;
    private final String value2;
    private final CacheTime cacheTime;

    @ConstructorBinding
    @RequiredArgsConstructor
    @Getter
    @ToString
    public static class CacheTime {
        private final int aCache;
        private final int bCache;
    }

}
