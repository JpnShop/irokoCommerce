package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Gender;
import finalproject.jpnshop.biz.domain.properties.Role;
import java.util.Date;
import javax.persistence.Column;
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


    @Builder
    public Member(Long id, String username, String password, String email, Gender gender,
        Date birthInfo, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthInfo = birthInfo;
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
