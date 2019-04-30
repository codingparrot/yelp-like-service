package interview.parrot.questions.yelplike.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author interviewparrot created on 18-Mar-2019
 */
@Data
@Builder
public class Response {
    private ResponseStatus status;
}
