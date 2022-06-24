package finalpractice.selfstudy.controller;

import finalpractice.selfstudy.dto.MemberRequestDto;
import finalpractice.selfstudy.dto.MemberResponseDto;
import finalpractice.selfstudy.entity.Address;
import finalpractice.selfstudy.entity.Member;
import finalpractice.selfstudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 요청처리: ModelAttribute vs RequestBody
    // https://mangkyu.tistory.com/72
    @PostMapping("/member")
    public String joinMemberV1(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        Address requestAddress = new Address(memberRequestDto.getCity(),
                memberRequestDto.getStreet(),
                memberRequestDto.getZipcode());

        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setAddress(requestAddress);

        memberService.join(member);

        return "ok";
    }

    // todo : result <> 클래스 따로 만들어야 하는 이유 이해하고 리펙터링하기
    @GetMapping("/member")
    public List<MemberResponseDto> findMembersV1() {
        List<Member> findMemberList = memberService.findMembers();

        List<MemberResponseDto> memberResponseDtos =
                findMemberList
                        .stream()
                        .map(m -> new MemberResponseDto(m.getName()))
                        .collect(Collectors.toList());

        return memberResponseDtos;
    }

}
