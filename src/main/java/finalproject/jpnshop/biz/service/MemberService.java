package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqPwd;
import finalproject.jpnshop.web.dto.ResMember;
import finalproject.jpnshop.web.dto.ResMember.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String updateEmail(String email) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (memberRepository.existsByEmail(email)
            && !memberRepository.findByEmail(email).getUsername().equals(member.getUsername())) {
            return "이미 존재하는 이메일입니다.";
        } else if(email.equals("")) {
            return "이메일을 입력하지 않았습니다.";
        } else {
            member.setEmail(email);
            return "이메일 정보가 변경되었습니다.";
        }
    }


    @Transactional
    public String updatePwd(ReqPwd pwd) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if(passwordEncoder.matches(pwd.getCurrentPwd(), member.getPassword())) {
            if(pwd.getNewPwd().equals(pwd.getVerifyNewPwd())) {
                member.setPassword(passwordEncoder.encode(pwd.getNewPwd()));
                return "비밀번호가 변경되었습니다.";
            } else {
                log.info(pwd.getNewPwd());
                log.info(pwd.getVerifyNewPwd());
                return "새 비밀번호가 일치하지 않습니다.";
            }
        } else {
            return "비밀번호가 일치하지 않습니다.";
        }
    }

    @Transactional(readOnly = true)
    public Response getMember(String username) {
        return memberRepository.findByUsername(username)
            .map(Response::of)
            .orElseThrow(()-> new RuntimeException("유저 정보가 없습니다."));
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public Response getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
            .map(Response::of)
            .orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> checkEmail(String email) {
        if(memberRepository.existsByEmail(email)) {
           throw new CustomException(ErrorCode.EMAIL_ALREADY_EXIST);
        } else {
            return ResponseEntity.ok("사용 가능한 이메일입니다.");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> checkUsername(String username) {
        if(memberRepository.existsByUsername(username)) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXIST);
        } else {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        }
    }


    @Transactional
    public String getTmpPassword() {
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String tmpPwd = "";

        int idx = 0;
        for(int i=0; i<10; i++) {
            idx = (int) (charSet.length * Math.random());
            tmpPwd += charSet[idx];
        }

        log.info("임시 비밀번호 생성");

        return tmpPwd;
    }

    @Transactional
    public void updateTmpPassword(String tmpPassword, String email) {
        String encryptPassword = passwordEncoder.encode(tmpPassword);
        Member member = memberRepository.findByEmail(email);

        member.updateTmpPassword(encryptPassword);
        log.info("임시 비밀번호 업데이트");
    }

    @Transactional
    public ResMember.Response getOrdersMember() {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        return ResMember.Response.from(member);
    }

}
