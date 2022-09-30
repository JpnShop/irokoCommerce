package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Role;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address;

    private String phoneNumber;

    private String country;
    private String furiganaFirst;
    private String furiganaLast;

    @Builder
    public Member(Long id, String username, String password, String email,
        String birthInfo, Role role, String name, Address address, String phoneNumber, String country, String furiganaFirst, String furiganaLast) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.furiganaFirst = furiganaFirst;
        this.furiganaLast = furiganaLast;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
