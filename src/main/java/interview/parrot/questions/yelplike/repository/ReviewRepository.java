package interview.parrot.questions.yelplike.repository;

import interview.parrot.questions.yelplike.entity.ReviewEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author interviewparrot created on 30-Mar-2019
 */
public interface ReviewRepository extends CrudRepository<ReviewEntity, String>  {
}
