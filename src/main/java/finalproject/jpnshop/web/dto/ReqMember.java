package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String country;
    private String firstFurigana;
    private String lastFurigana;

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .email(email)
            .role(Role.ROLE_USER)
            .firstName(firstName)
            .lastName(lastName)
            .phoneNumber(phoneNumber)
            .address(address)
            .country(country)
            .firstFurigana(firstFurigana)
            .lastFurigana(lastFurigana)
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
            ", role= " + role +
            ", firstName= " + firstName +
            ", lastName= " + lastName +
            ", phoneNumber= " + phoneNumber +
            ", address= " + address +
            ", country= " + country +
            ", firstFurigana= " + firstFurigana +
            ", lastFurigana= " + lastFurigana +
            '}';
    }
}
