package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.RefreshToken;
import finalproject.jpnshop.biz.repository.MemberRepository;
import finalproject.jpnshop.biz.repository.RefreshTokenRepository;
import finalproject.jpnshop.jwt.TokenProvider;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ReqToken;
import finalproject.jpnshop.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void signup(ReqMember reqMember) {
        if(memberRepository.existsByUsername(reqMember.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Member member = reqMember.toEntity(passwordEncoder);
        memberRepository.save(member);
    }

    @Transactional
    public TokenDto login(ReqMember reqMember) {
        UsernamePasswordAuthenticationToken authenticationToken = reqMember.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
            .key(authentication.getName())
            .value(tokenDto.getRefreshToken())
            .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(ReqToken reqToken) {

        if(!tokenProvider.validateToken(reqToken.getRefreshToken())) {
            throw new RuntimeException("Refresh Token이 유효하지 않습니다.");
        }

        Authentication authentication = tokenProvider.getAuthentication(reqToken.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
            .orElseThrow(() -> new RuntimeException("로그아웃된 사용자입니다."));

        if(!refreshToken.getValue().equals(reqToken.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        return tokenDto;
    }


}
