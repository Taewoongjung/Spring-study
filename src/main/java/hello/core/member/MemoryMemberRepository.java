package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 그냥 HashMap 을 사용하면 동시성 이슈가 발생할 수 있어서 원래는
                                                              // concurrent hashmap 을 사용한다. 그런데 여기서는 그냥 썻다.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
