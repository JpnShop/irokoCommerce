package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.properties.Gender;
import finalproject.jpnshop.web.dto.ResMember.Response;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ResMember {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private String username;
        private String password;
        private String email;
        private Gender gender;
        private Date birthInfo;
        private  String role;

        public static Response of(Member member) {
            return Response.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .gender(member.getGender())
                .birthInfo(member.getBirthInfo())
                .role(member.getRole())
                .build();
        }

    }

}
