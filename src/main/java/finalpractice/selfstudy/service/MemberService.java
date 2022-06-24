package finalpractice.selfstudy.service;

import finalpractice.selfstudy.entity.Member;
import finalpractice.selfstudy.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 등록
    @Transactional
    public Long join(Member member){
        isDuplicatedMember(member); // 중복회원 검증
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    // 중복확인
    public void isDuplicatedMember(Member member){
        // 중복 확인처리는 모두 이 로직으로 하고 넘어가기. 다른 함수로 올리지 말기.
        if(!memberRepository.findByName(member.getName()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


    //회원 목록 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

}
