package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResDeliveryInfo {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        private Address address;
        private String phoneNumber;
        private String firstName;
        private String lastName;
        private String firstFurigana;
        private String lastFurigana;
        private String orderNum;

        public static Response of(DeliveryInfo deliveryInfo) {
            return Response.builder()
                .address(deliveryInfo.getAddress())
                .phoneNumber(deliveryInfo.getPhoneNumber())
                .firstName(deliveryInfo.getFirstName())
                .lastName(deliveryInfo.getLastName())
                .firstFurigana(deliveryInfo.getFirstFurigana())
                .lastFurigana(deliveryInfo.getLastFurigana())
                .orderNum(deliveryInfo.getOrder().getOrderNum())
                .build();
        }
    }

}
