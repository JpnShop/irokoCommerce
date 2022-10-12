package finalproject.jpnshop.biz.domain;

import finalproject.jpnshop.biz.domain.properties.Role;
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

    public static final int LAST_PHONE_NUM_POSITION = 4;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address;

    private String phoneNumber;

    private String country;
    private String firstFurigana;
    private String lastFurigana;

    @Builder
    public Member(Long id, String username, String password, String email,
        String birthInfo, Role role, String firstName, String lastName, Address address, String phoneNumber, String country, String firstFurigana, String lastFurigana) {
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
        this.firstFurigana = firstFurigana;
        this.lastFurigana = lastFurigana;
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

    public void updateTmpPassword(String tmpPassword) {
        this.password = tmpPassword;
    }

    public String lastPhoneNum() {
        String phoneNum = getPhoneNumber();
        int pos = phoneNum.length() - LAST_PHONE_NUM_POSITION;
        return phoneNum.substring(pos);

    }

}
