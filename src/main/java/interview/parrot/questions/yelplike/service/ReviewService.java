package interview.parrot.questions.yelplike.service;

import interview.parrot.questions.yelplike.entity.ReviewEntity;
import interview.parrot.questions.yelplike.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author interviewparrot created on 30-Mar-2019
 */
public class ReviewService {

    @Autowired
    ReviewRepository repository;

    public void saveOrUpdateReview(ReviewEntity review) {
        repository.save(review);
    }
}
