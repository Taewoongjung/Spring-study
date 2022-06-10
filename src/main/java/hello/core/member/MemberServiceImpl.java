package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 이렇게 하면 추상화에도 의존하고 구현체에도 의존한다. 고로 DIP 를 위반하고 있다.
                                                                                    // 이것을 DIP 를 위반 안하게 하려면..? 고민해보자.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
