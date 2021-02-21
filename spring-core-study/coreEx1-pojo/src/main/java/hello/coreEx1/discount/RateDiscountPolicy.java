package hello.coreEx1.discount;

import hello.coreEx1.member.Grade;
import hello.coreEx1.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private static final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return getDiscountPrice(price);
        }
        return 0;
    }

    private int getDiscountPrice(int price) {
        return price * discountPercent / 100;
    }
}
