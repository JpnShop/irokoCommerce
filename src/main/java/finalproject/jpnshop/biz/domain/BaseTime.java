package finalproject.jpnshop.biz.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {

    @Column(name = "createdAt")
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "modifiedAt")
    @LastModifiedDate
    private LocalDate modifiedAt;

}
