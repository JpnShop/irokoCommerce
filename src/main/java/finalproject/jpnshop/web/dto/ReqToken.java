package finalproject.jpnshop.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReqToken {
    private String accessToken;
    private String refreshToken;
}
