package hello.coreEx1.order;

import hello.coreEx1.AppConfig;
import hello.coreEx1.member.Grade;
import hello.coreEx1.member.Member;
import hello.coreEx1.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "bobaeTest", Grade.VIP);
        memberService.join(member);

        String itemName = "Macbook";
        int itemPrice = 3_000_000;
        Order order = orderService.createOrder(memberId, "Macbook", itemPrice);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.calculatePrice()).isEqualTo(2_999_000);

    }
}