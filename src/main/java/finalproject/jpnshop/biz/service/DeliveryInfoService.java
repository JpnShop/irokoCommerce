package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.DeliveryInfoRepository;
import finalproject.jpnshop.biz.repository.OrderRepository;
import finalproject.jpnshop.web.dto.ResDeliveryInfo;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class DeliveryInfoService {

    private final DeliveryInfoRepository deliveryInfoRepository;
    private final OrderRepository orderRepository;

    public ResDeliveryInfo.Response getDelivery(Long deliveryInfoId) {

        DeliveryInfo deliveryInfo = deliveryInfoRepository.findById(deliveryInfoId).orElseThrow(
            () -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND)
        );
        return ResDeliveryInfo.Response.of(deliveryInfo);
    }

}
