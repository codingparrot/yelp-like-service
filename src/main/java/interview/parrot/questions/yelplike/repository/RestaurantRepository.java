package interview.parrot.questions.yelplike.repository;

import interview.parrot.questions.yelplike.entity.RestaurantEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
public interface RestaurantRepository extends CrudRepository<RestaurantEntity, String> {

    List<RestaurantEntity> findByName(String name);
}
