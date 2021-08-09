package com.redis.demo;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@AllArgsConstructor
@RedisHash(value = "member")
public class Member {

    @Id
    private final Long id;

    @TimeToLive
    private final Long ttl;

}
