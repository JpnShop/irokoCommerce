package finalproject.jpnshop.biz.domain.properties;

import lombok.Getter;

public enum PayMethod {
    CARD("신용 카드"), BANK("무통장 입금"), APPLE("애플페이"), LINE("라인페이");

    @Getter
    public String korean;

    PayMethod(String kor) {
        this.korean = kor;
    }
}
