package finalproject.jpnshop.biz.exception;

import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;

}
