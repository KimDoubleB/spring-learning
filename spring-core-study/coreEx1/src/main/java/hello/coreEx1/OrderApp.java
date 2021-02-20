package hello.coreEx1;

import hello.coreEx1.member.Grade;
import hello.coreEx1.member.Member;
import hello.coreEx1.member.MemberService;
import hello.coreEx1.member.MemberServiceImpl;
import hello.coreEx1.order.Order;
import hello.coreEx1.order.OrderService;
import hello.coreEx1.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "Bobae", Grade.VIP);
        memberService.join(member); // 가입

        Order order = orderService.createOrder(memberId, "Macbook", 10000);
        System.out.println("Order : " + order);
        System.out.println("New Price: " + order.calculatePrice());
    }
}
