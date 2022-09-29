package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Gender;
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
    private Gender gender;

    //TODO
    private Date birthInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String name;

    @Embedded
    private Address address;

    private String phoneNumber;

    private String country;
    private String furigana;

    @Builder
    public Member(Long id, String username, String password, String email, Gender gender,
        Date birthInfo, Role role, String name, Address address, String phoneNumber, String country, String furigana) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthInfo = birthInfo;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.furigana = furigana;
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
