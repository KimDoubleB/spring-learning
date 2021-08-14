package com.cookie.demo;

public class Member {
    public static final String KEY = "member";
    private final Long memberId;
    private final String name;

    private Member(Long memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public static Member create(Long memberId, String name) {
        return new Member(memberId, name);
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }



}
