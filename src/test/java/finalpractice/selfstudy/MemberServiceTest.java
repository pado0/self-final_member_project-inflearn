package finalpractice.selfstudy;

import finalpractice.selfstudy.entity.Member;
import finalpractice.selfstudy.repository.MemberRepository;
import finalpractice.selfstudy.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() {
        // given
        Member member = new Member();
        member.setName("member1");

        // when
        Member savedMember = memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findById(savedMember.getId());

        // then
        assertThat(savedMember.getName()).isEqualTo(findMember.get().getName());
    }

    @Test(expected = IllegalStateException.class)
     public void 중복체크() throws Exception {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("member1");
        member2.setName("member1");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 회원목록조회() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("member3");
        member2.setName("member4");

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        assertThat(memberService.findMembers().stream().count()).isEqualTo(2);
    }
}