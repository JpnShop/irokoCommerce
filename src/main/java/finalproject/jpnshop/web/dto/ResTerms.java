//package finalproject.jpnshop.web.dto;
//
//import finalproject.jpnshop.biz.domain.Terms;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//
//public class ResTerms {
//
//    @Getter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class Response {
//        private Long id;
//        private String ServiceContent;
//        private String PersonalContent;
//
//
//        public static Response of(Terms agreement) {
//            return Response.builder()
//                .ServiceContent(agreement.getServiceContent())
//                .PersonalContent(agreement.getPersonalContent())
//                .build();
//        }
//
//    }
//}
