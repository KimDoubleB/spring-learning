package hello.coreEx1;

import hello.coreEx1.discount.DiscountPolicy;
import hello.coreEx1.discount.FixDiscountPolicy;
import hello.coreEx1.member.MemberService;
import hello.coreEx1.member.MemberServiceImpl;
import hello.coreEx1.member.MemoryMemberRepository;
import hello.coreEx1.order.OrderService;
import hello.coreEx1.order.OrderServiceImpl;

public class AppConfig {
    // DI (Dependency Injection): 의존관계 주입

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
