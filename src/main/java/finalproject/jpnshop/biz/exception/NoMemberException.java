package finalproject.jpnshop.biz.exception;

import lombok.NoArgsConstructor;

public class NoMemberException extends RuntimeException{
    public NoMemberException(String msg){
        super (msg);
    }

    public NoMemberException(Exception ex){
        super (ex);
    }


}
