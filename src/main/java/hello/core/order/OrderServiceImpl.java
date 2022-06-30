package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 생성자를 자동으로 만들어준다. 그래서 코드를 줄일 수 있다.
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 기획자로 인해 discountPolicy 가 변경 되었다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이거를
    private final DiscountPolicy discountPolicy; // 이렇게 변경되었다. (final 은 무조건 값이 할당되어야 하기 때문에 여기는 값이 없으니 지움)

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 이렇게 하면 철저히 DIP 를 지키고 있다.
//        this.memberRepository = memberRepository; // 인터페이스에만 의존하기 때문에 DIP 를 지키고 있다는 뜻이다. ( = 여기에서는 구체적인 클래스에 대해 전혀 모른다)
//        this.discountPolicy = discountPolicy;
//    }

    /* MEMO
    *
    *  추상(인터페이스) 의존: DiscountPolicy
    *  구체(구현) 클래스 의존: FixDiscountPolicy, RateDiscountPolicy
    *
    *  위 상황에 의거해서 원래 추상에만 의존 해야하지만 구체 클래스에도 의존하고 있기 때문에 "DIP" 를 위반중임.
    *
    *  그리고 실제 코드가 바뀌면 안되는데 바뀌었으니 OCP 도 위반중임.
    *
    * */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
