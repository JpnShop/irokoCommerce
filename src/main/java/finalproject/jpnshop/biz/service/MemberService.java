package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.util.SecurityUtil;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ResMember.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public String updateMember(ReqMember memberForm) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            ()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if(memberRepository.existsByEmail(memberForm.getEmail())
            && !memberRepository.findByEmail(memberForm.getEmail()).getUsername().equals(member.getUsername())) {
          return "이미 존재하는 이메일입니다.";
        } else if(memberForm.getEmail().equals("")) {
            return "이메일을 입력하지 않았습니다.";
        } else {
            member.setPassword(bCryptPasswordEncoder.encode(memberForm.getPassword()));
            member.setEmail(memberForm.getEmail());
            return "수정이 완료되었습니다.";

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
    public String checkEmail(String email) {
        if(memberRepository.existsByEmail(email)) {
            return "이메일이 존재합니다.";
        }
        return "이메일이 존재하지 않습니다.";
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
        String encryptPassword = bCryptPasswordEncoder.encode(tmpPassword);
        Member member = memberRepository.findByEmail(email);

        member.updateTmpPassword(encryptPassword);
        log.info("임시 비밀번호 업데이트");
    }

}
