package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Gender;
import finalproject.jpnshop.biz.domain.properties.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTime{

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

}
