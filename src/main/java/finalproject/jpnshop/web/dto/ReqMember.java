package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.Gender;
import finalproject.jpnshop.biz.domain.properties.Role;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqMember {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Gender gender;
    private Date birthInfo;
    private String role;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .email(email)
            .gender(gender)
            .birthInfo(birthInfo)
            .role(Role.ROLE_USER)
            .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ReqMember{" +
            "id=" + id +
            ", username= " + username +
            ", password= " + password +
            ", email= " + email +
            ", gender= " + gender +
            ", birthInfo= " + birthInfo +
            ", role= " + role + '}';
    }
}
