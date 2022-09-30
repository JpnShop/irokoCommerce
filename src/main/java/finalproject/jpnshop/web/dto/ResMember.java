package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.Member;
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
        private String birthInfo;
        private String role;
        private String name;
        private String phoneNumber;
        private Address address;
        private String country;
        private String furiganaFirst;
        private String furiganaLast;


        public static Response of(Member member) {
            return Response.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .birthInfo(member.getBirthInfo())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .country(member.getCountry())
                .furiganaFirst(member.getFuriganaFirst())
                .furiganaLast(member.getFuriganaLast())
                .build();
        }

    }

}
