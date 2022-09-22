package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.web.dto.ReqMember;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원정보를 받아 등록한다.")
    @ApiImplicitParam(name= "memberForm", value = "회원정보", dataType = "ReqMember", required = true)
    @PostMapping("/members")
    public String insertMember(@RequestBody ReqMember memberForm) {
        memberService.insertMember(memberForm);
        return "회원가입이 완료되었습니다.";
    }

    @PutMapping("/member")
    @ApiOperation(value = "회원정보 수정", notes = "새로 정보를 입력하여 회원정보를 수정한다.")
    @ApiImplicitParam(name="memberForm", value = "회원정보", dataType = "ReqMember", required = true)
   public String updateMember(@RequestBody ReqMember memberForm) {
        memberService.updateMember(memberForm);
        return "회원정보 수정이 완료되었습니다.";
    }

    @ApiOperation(value = "멤버 상세 조회", notes = "회원을 조회해서 정보를 가져온다.")
    @ApiImplicitParam(name = "username", value = "회원 아이디", dataType = "String", required = true)
    @GetMapping("/member")
    public void getMember(String username) {
        memberService.getMember(username);
    }
}
