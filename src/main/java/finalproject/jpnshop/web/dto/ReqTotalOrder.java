package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.properties.PayMethod;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ReqTotalOrder {

    private List<ReqOrder> totalOrders = new ArrayList<>();
    private PayMethod payMethod;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReqOrder {
        private Long productId;
        private Integer count;
    }

}
