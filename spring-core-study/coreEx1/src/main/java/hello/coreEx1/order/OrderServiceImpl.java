package hello.coreEx1.order;

import hello.coreEx1.discount.DiscountPolicy;
import hello.coreEx1.discount.FixDiscountPolicy;
import hello.coreEx1.member.Member;
import hello.coreEx1.member.MemberRepository;
import hello.coreEx1.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
