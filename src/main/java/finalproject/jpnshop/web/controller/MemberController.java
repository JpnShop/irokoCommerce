package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.service.MailService;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.web.dto.MailDto;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ResMember.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Member 정보를 수정/조회 가능한 Controller"})
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @PutMapping("/members")
    @ApiOperation(value = "회원정보 수정", notes = "새로 정보를 입력하여 회원정보를 수정한다.")
    @ApiImplicitParam(name="memberForm", value = "회원정보", dataType = "ReqMember", required = true)
    public String updateMember(@RequestBody ReqMember memberForm) {
        return memberService.updateMember(memberForm);
    }

    @ApiOperation(value = "멤버 상세 조회", notes = "회원을 조회해서 정보를 가져온다.")
    @GetMapping("/members/{username}")
    public ResponseEntity<Response> getMember(@PathVariable String username) {
        return ResponseEntity.ok(memberService.getMember(username));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @GetMapping("/members")
    public ResponseEntity<Response> getMyInfo() {
        return ResponseEntity.ok(memberService.getMyInfo());
    }

    @GetMapping("/checkEmail")
    public String checkEmail(@RequestBody String email) {
        log.info("checkEmail 진입");
        return memberService.checkEmail(email);
    }

    @PostMapping("/newPwd")
    public void sendPwdEmail(@RequestBody String email) {
        log.info("sendPwdEmail 진입");
        log.info("이메일: " + email);

        String tmpPassword = memberService.getTmpPassword();

        memberService.updateTmpPassword(tmpPassword, email);

        MailDto mail = mailService.createPwdMail(tmpPassword, email);
        mailService.sendEmail(mail);

        log.info("임시 비밀번호 저장 성공");
    }

    @PostMapping("/sendUsername")
    public String sendUsername(@RequestBody ReqMember memberForm) {
        Member member = memberRepository.findByEmail(memberForm.getEmail());

        if(passwordEncoder.matches(memberForm.getPassword(), member.getPassword()) == true) {
            MailDto mail = mailService.createUsernameMail(member.getEmail(), member.getUsername());
            mailService.sendEmail(mail);

            log.info("Username 메일 전송 성공");
            return "아이디 안내 메일을 발송했습니다.";
        } else {
            return "잘못된 회원 정보입니다.";
        }

    }

}
