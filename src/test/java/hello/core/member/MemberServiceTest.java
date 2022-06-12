package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", 25L, Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void exit() {
        // given
        Member member = new Member(1L, "memberA", 22L, Grade.BASIC);

        // when
        memberService.join(member);
        memberService.exit(member.getId());
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(findMember).isEqualTo(null);
    }
}
