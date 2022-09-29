package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.service.AuthService;
import finalproject.jpnshop.web.dto.ReqMember;
import finalproject.jpnshop.web.dto.ReqToken;
import finalproject.jpnshop.web.dto.TokenDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = {"회원가입/로그인 가능한 Controller"})
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody ReqMember reqMember) {
        authService.signup(reqMember);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody ReqMember reqMember) {
        return ResponseEntity.ok(authService.login(reqMember));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody ReqToken reqToken) {
        return ResponseEntity.ok(authService.reissue(reqToken));
    }

    @GetMapping("/Glogin")
    public ResponseEntity<TokenDto> Glogin(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return ResponseEntity.ok(authService.Glogin(oAuth2User));
    }
}
