package com.cookie.demo;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    Map<Long, Member> members = new ConcurrentHashMap<>();

    public void save(Member member) {
        members.put(member.getMemberId(), member);
    }

    public Member findById(Long id) {
        return members.get(id);
    }

}
