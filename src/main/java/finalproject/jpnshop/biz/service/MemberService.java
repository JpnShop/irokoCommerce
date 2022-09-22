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
    public void updateMember(ReqMember memberForm) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(
            ()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        member.setPassword(bCryptPasswordEncoder.encode(memberForm.getPassword()));
        member.setEmail(memberForm.getEmail());
    }

    @Transactional(readOnly = true)
    public Response getMember(String username) {
        return memberRepository.findByUsername(username)
            .map(Response::of)
            .orElseThrow(()-> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional(readOnly = true)
    public Response getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
            .map(Response::of)
            .orElseThrow(()-> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


}
