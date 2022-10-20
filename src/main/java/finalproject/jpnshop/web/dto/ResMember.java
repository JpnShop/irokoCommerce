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
        private String role;
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private Address address;
        private String country;
        private String firstFurigana;
        private String lastFurigana;

        public static Response from(Member member) {
            return Response.builder()
                .address(member.getAddress())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .firstFurigana(member.getFirstFurigana())
                .lastFurigana(member.getLastFurigana())
                .phoneNumber(member.getPhoneNumber())
                .build();
        }
        public static Response of(Member member) {
            return Response.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .country(member.getCountry())
                .firstFurigana(member.getFirstFurigana())
                .lastFurigana(member.getLastFurigana())
                .build();
        }

    }

}
