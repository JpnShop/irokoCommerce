package finalproject.jpnshop.biz.domain.properties;

import lombok.Getter;

public enum DeliveryStatus {
    PAY("결제 완료"), SHIPPING("배송 중"), CANCEL("취소"), COMPLETE("배송 완료");

    @Getter
    public String korean;

    DeliveryStatus(String kor) {
        this.korean = kor;
    }

}
