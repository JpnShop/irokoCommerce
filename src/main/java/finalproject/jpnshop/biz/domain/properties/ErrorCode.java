package finalproject.jpnshop.biz.domain.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404,"회원 정보를 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(404,"상품 정보를 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(404, "리뷰를 찾을 수 없습니다." ),
    INTERNAL_SERVER_ERROR(500, "서버 에러가 발생했습니다. 고객센터로 문의 바랍니다."),
    NOTICE_NOT_FOUND(404, "공지글을 찾을 수 없습니다.");

    private final int status;
    private final String msg;
}
