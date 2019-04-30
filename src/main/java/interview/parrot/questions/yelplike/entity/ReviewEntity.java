package interview.parrot.questions.yelplike.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author interviewparrot created on 18-Mar-2019
 */
@Entity
@Data
@Table(name="review")
public class ReviewEntity extends BaseModifiableEntity {

    @Id
    private String reviewId;

    private String userId;

    private String businessId;

    private String text;

    private int stars;

    private int useful;

    private int funny;

    private int cool;

}
