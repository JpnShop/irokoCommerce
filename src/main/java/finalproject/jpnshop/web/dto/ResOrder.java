package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.Member;
import finalproject.jpnshop.biz.domain.Order;
import finalproject.jpnshop.biz.domain.properties.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ResOrder {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public static class Response {

        private Status status;
        private Member member;
        private DeliveryInfo deliveryInfo;

        public static Response of(Order order) {
            return Response.builder()
                .status(order.getStatus())
                .member(order.getMember())
                .deliveryInfo(order.getDeliveryInfo())
                .build();
        }

    }

}
