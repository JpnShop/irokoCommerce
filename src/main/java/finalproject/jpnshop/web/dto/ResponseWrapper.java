package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseWrapper {

    private final ResMember.Response resMember;
    private final List<Response> resProduct;
    private final ResDeliveryInfo.Response resDeliveryInfo;


}
