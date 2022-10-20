package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Address;
import finalproject.jpnshop.biz.domain.DeliveryInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReqDeliveryInfo {
    private Address address;
    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String firstFurigana;

    private String lastFurigana;


    public DeliveryInfo toEntity() {
        return DeliveryInfo.builder()
            .address(address)
            .phoneNumber(phoneNumber)
            .firstName(firstName)
            .lastName(lastName)
            .firstFurigana(firstFurigana)
            .lastFurigana(lastFurigana)
            .build();
    }

    @Override
    public String toString() {
        return "ReqDeliveryInfo{" +
            "address=" + address +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", firstFurigana='" + firstFurigana + '\'' +
            ", lastFurigana='" + lastFurigana + '\'' +
            '}';
    }
}
