package com.redis.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.stream.LongStream;

@SpringBootTest
class RedisTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 멤버를_저장할_수_있다() {
        var members = new ArrayList<Member>();
        LongStream.range(0L, 10L).forEach(ind -> {
            members.add(new Member(ind, 50L));
        });
        memberRepository.saveAll(members);
    }

}
