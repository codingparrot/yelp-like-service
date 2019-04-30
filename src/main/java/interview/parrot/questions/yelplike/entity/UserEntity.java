package interview.parrot.questions.yelplike.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author interviewparrot created on 18-Mar-2019
 */
@Entity
@Data
@Table(name="user")
public class UserEntity extends BaseModifiableEntity {

    @Id
    private String userId;

    private String name;

    private int reviewCount;

    private Date joiningDate;
}
