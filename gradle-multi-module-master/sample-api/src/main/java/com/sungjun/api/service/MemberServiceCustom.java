package com.sungjun.api.service;

import com.sungjun.common.member.Member;
import com.sungjun.common.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceCustom {

    private MemberRepository memberRepository;

    public MemberServiceCustom(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long singup (Member member) {
        return memberRepository.save(member).getId();
    }
}
