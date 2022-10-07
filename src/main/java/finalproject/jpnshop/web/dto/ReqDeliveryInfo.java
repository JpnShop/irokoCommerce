package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.DeliveryInfo;
import finalproject.jpnshop.biz.domain.properties.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqDeliveryInfo {
    private Address address;
    private String phoneNumber;
    private String receiverName;
    private DeliveryStatus status;


    public DeliveryInfo toEntity() {
        return DeliveryInfo.builder()
            .address(address)
            .phoneNumber(phoneNumber)
            .receiverName(receiverName)
            .status(status)
            .build();
    }

    @Override
    public String toString() {
        return "ReqDeliveryInfo{" +
            "address=" + address +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", receiverName='" + receiverName + '\'' +
            ", status=" + status +
            '}';
    }
}
