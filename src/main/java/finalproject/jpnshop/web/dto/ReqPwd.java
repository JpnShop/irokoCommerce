package finalproject.jpnshop.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqPwd {

    private String currentPwd;
    private String newPwd;
    private String verifyNewPwd;

}
