package hello.coreEx1.discount;

import hello.coreEx1.member.Grade;
import hello.coreEx1.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 할인이 되어야 한다.")
    void vipDiscountO(){
        // given
        Member member = new Member(1L, "Bobae", Grade.VIP);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 되어서는 안된다.")
    void vipDiscountX(){
        // given
        Member member = new Member(1L, "Bobae2", Grade.BASIC);
        DiscountPolicy discountPolicy = new RateDiscountPolicy();

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);
    }

}