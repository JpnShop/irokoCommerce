package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.web.dto.ReqMember;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

}
