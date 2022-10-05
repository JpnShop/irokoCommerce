package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.properties.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResDeliveryInfo {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{

        private Order order;
        private Address address;
        private String phoneNumber;
        private String receiverName;
        private DeliveryStatus status;

        public static Response of(DeliveryInfo deliveryInfo) {
            return Response.builder()
                .order(deliveryInfo.getOrder())
                .address(deliveryInfo.getAddress())
                .phoneNumber(deliveryInfo.getPhoneNumber())
                .receiverName(deliveryInfo.getReceiverName())
                .status(deliveryInfo.getStatus())
                .build();
        }
    }

}
