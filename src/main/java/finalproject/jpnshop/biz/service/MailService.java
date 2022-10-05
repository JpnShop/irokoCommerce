package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.web.dto.MailDto;
import finalproject.jpnshop.web.dto.ReqMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MemberRepository memberRepository;

    private static final String pwdTitle = "임시 비밀번호 안내 이메일입니다.";
    private static final String usernameTitle = "아이디 안내 이메일입니다.";
    private static final String pwdMessage = "안녕하세요. 임시 비밀번호 안내 메일입니다."
        + "\n" + "\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요." + "\n";

    private static final String usernameMessage = "안녕하세요. 아이디 찾기 안내 메일입니다." + "\n" + "회원님의 아이디는 아래와 같습니다." + "\n";

    private static final String fromAddress = "wndgn456@gmail.com";

    public MailDto createPwdMail(String tmpPassword, String email) {
        MailDto mailDto = MailDto.builder()
            .toAddress(email)
            .title(pwdTitle)
            .message(pwdMessage + "\n" + tmpPassword)
            .fromAddress(fromAddress)
            .build();

        log.info("비밀번호 메일 생성 완료");
        return mailDto;
    }

    public MailDto createUsernameMail(String email, String username) {

            MailDto mailDto = MailDto.builder()
                .toAddress(email)
                .title(usernameTitle)
                .message(usernameMessage + "\n" + username)
                .fromAddress(fromAddress)
                .build();

            log.info("아이디 메일 생성 완료");
            return mailDto;
    }

    public void sendEmail(MailDto mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailDto.getToAddress());
        mailMessage.setSubject(mailDto.getTitle());
        mailMessage.setText(mailDto.getMessage());
        mailMessage.setFrom(mailDto.getFromAddress());
        mailMessage.setReplyTo(mailDto.getFromAddress());

        mailSender.send(mailMessage);

        log.info("메일 전송 완료");
    }
}
