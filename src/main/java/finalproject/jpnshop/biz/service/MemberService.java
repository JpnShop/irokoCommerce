package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ResMember;
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
    public void insertMember(ReqMember memberForm) {
        String encodedPassword = bCryptPasswordEncoder.encode(memberForm.getPassword());
        memberForm.setPassword(encodedPassword);
        memberForm.setRole("ROLE_USER");
        Member member = memberForm.toEntity();

        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(ReqMember memberForm) {
        Member member = memberRepository.findById(memberForm.getId()).orElseThrow(
            ()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        member.setPassword(bCryptPasswordEncoder.encode(memberForm.getPassword()));
        member.setEmail(memberForm.getEmail());
    }

    @Transactional(readOnly = true)
    public Response getMember(String username) {
        return Response.of(memberRepository.findByUsername(username));
    }

}
