package finalproject.jpnshop.web.dto;

import finalproject.jpnshop.biz.domain.Review;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReqImage {

    private long id;

    private Review review;
    private String origFileName;  // 파일 원본명
    private String filePath;  // 파일 저장 경로
    private Long fileSize;

    @Builder
    public ReqImage(String origFileName, String filePath, Long fileSize) {
        this.origFileName = origFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
