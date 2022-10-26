package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.service.MailService;
import finalproject.jpnshop.biz.service.MemberService;
import finalproject.jpnshop.web.dto.MailDto;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ReqPwd;
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

    @PutMapping("/members/email")
    @ApiOperation(value = "이메일 수정", notes = "새로운 이메일를 입력하여 회원정보를 수정한다.")
    @ApiImplicitParam(name="email", value = "이메일", dataType = "String", required = true)
    public String updateEmail(@RequestBody String email) {
        return memberService.updateEmail(email);
    }

    @PutMapping("/members/pwd")
    @ApiOperation(value = "비밀번호 수정", notes = "새로운 비밀번호를 입력하여 회원정보를 수정한다.")
    @ApiImplicitParam(name="pwdForm", value = "비밀번호", dataType = "ReqPwd", readOnly = true)
    public String updatePwd(@RequestBody ReqPwd pwdForm) {
        return memberService.updatePwd(pwdForm);
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

    @GetMapping("/checkEmail/{email}")
    public String checkEmail(@PathVariable String email) {
        log.info("checkEmail 진입");
        return memberService.checkEmail(email);
    }

    @GetMapping("/checkUsername/{username}")
    public String checkUsername(@PathVariable String username) {
        return memberService.checkUsername(username);
    }

    @PostMapping("/newPwd")
    public String sendPwdEmail(@RequestBody ReqMember memberForm) {
        log.info("sendPwdEmail 진입");
        log.info("이메일: " + memberForm.getEmail());

        Member member = memberRepository.findByEmail(memberForm.getEmail());

        if(member.getUsername().equals(memberForm.getUsername())) {

            String tmpPassword = memberService.getTmpPassword();
            memberService.updateTmpPassword(tmpPassword, member.getEmail());

            MailDto mail = mailService.createPwdMail(tmpPassword, member.getEmail());
            mailService.sendEmail(mail);

            log.info("임시 비밀번호 저장 성공");
            return "임시 비밀번호 안내 메일을 발송했습니다.";
        } else {
            return "잘못된 회원 정보입니다.";
        }
    }

    @PostMapping("/sendUsername")
    public String sendUsername(@RequestBody ReqMember memberForm) {
        Member member = memberRepository.findByEmail(memberForm.getEmail());

        if(memberRepository.existsByEmail(memberForm.getEmail())) {
            MailDto mail = mailService.createUsernameMail(member.getEmail(), member.getUsername());
            mailService.sendEmail(mail);

            log.info("Username 메일 전송 성공");
            return "아이디 안내 메일을 발송했습니다.";
        } else {
            return "존재하지 않는 이메일입니다.";
        }

    }

}