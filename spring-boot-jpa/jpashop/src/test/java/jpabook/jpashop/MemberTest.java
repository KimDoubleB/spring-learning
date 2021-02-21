package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional  // Test코드인 경우 자동으로 롤백
    @Rollback(false)
    public void memberTest() {
        // given
        Member member = new Member();
        member.setUsername("Member A");
        Long savedId = memberRepository.save(member);

        // when
        Member findMember = memberRepository.find(savedId);

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember == member).isTrue();

    }

}