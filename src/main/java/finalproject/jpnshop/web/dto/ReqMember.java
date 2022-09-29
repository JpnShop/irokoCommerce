package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
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
    private String birthInfo;
    private String role;
    private String name;
    private String phoneNumber;
    private Address address;
    private String country;
    private String furiganaFirst;
    private String furiganaLast;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .email(email)
            .gender(gender)
            .birthInfo(birthInfo)
            .role(Role.ROLE_USER)
            .name(name)
            .phoneNumber(phoneNumber)
            .address(address)
            .country(country)
            .furiganaFirst(furiganaFirst)
            .furiganaLast(furiganaLast)
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

    public void setAddress(Address address) {
        this.address = address;
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
            ", role= " + role +
            ", name= " + name +
            ", phoneNumber= " + phoneNumber +
            ", address= " + address +
            ", country= " + country +
            ", furiganaFirst= " + furiganaFirst +
            ", furiganaLast= " + furiganaLast +
            '}';
    }
}
