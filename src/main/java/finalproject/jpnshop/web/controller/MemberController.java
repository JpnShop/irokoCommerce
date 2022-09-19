package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.web.dto.ReqMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;


    @PostMapping("/users")
    public String insertMember(@RequestBody ReqMember memberForm) {
        memberService.insertMember(memberForm);
        return "회원가입이 완료되었습니다.";
    }

}
