package interview.parrot.questions.yelplike.dto;

import lombok.Data;

/**
 * @Author interviewparrot created on 30-Mar-2019
 */
@Data
public class Review {


    private String userId;

    private String businessId;

    private String text;

    private int stars;

    private int useful;

    private int funny;

    private int cool;
}
