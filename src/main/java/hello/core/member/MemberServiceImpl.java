package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 이렇게 하면 추상화에도 의존하고 구현체에도 의존한다. 고로 DIP 를 위반하고 있다.
                                                                                    // 이것을 DIP 를 위반 안하게 하려면..? => 밑에 처럼 해야한다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자를 통해서 어떤 Repository 가 들어갈지 정해준다.
        this.memberRepository = memberRepository;                 // 이런거를 생성자를 통해서 구현 객체가 들어간다고 해서 생성자 주입이라고 한다.
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member exit(Long memberId) {
        return memberRepository.deleteMember(memberId);
    }
}
