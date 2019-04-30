package interview.parrot.questions.yelplike.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
@Data
@MappedSuperclass
public abstract class BaseModifiableEntity implements Serializable {

    private String createdBy;

    @CreationTimestamp
    private Date creationTime;

    @LastModifiedBy
    private String lastModifiedBy;

    @UpdateTimestamp
    private Date lastModifiedTime;

    @Version
    private Integer version;

}
