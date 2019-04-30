package interview.parrot.questions.yelplike.service;

import com.google.common.collect.Lists;
import interview.parrot.questions.yelplike.entity.RestaurantEntity;
import interview.parrot.questions.yelplike.exception.InvalidInputException;
import interview.parrot.questions.yelplike.repository.RestaurantRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private Hashids hashids;

    public RestaurantEntity get(String name) throws InvalidInputException {
        List<RestaurantEntity> restaurantEntities = repository.findByName(name);
        if(restaurantEntities == null || restaurantEntities.isEmpty()) {
            throw new InvalidInputException("Restaurant name doesn't exists");
        }
        return repository.findByName(name).get(0);
    }

    public List<RestaurantEntity> list() {
        return Lists.newArrayList(repository.findAll());
    }

    /**
     * Saves or updates a Restaurant information.
     * @param restaurantEntity
     */
    public void saveOrUpdate(RestaurantEntity restaurantEntity) throws InvalidInputException {
        if(!StringUtils.isEmpty(restaurantEntity.getBusinessId())) {
            Optional<RestaurantEntity> restaurantEntityOptional = repository.findById(restaurantEntity.getBusinessId());
            if(restaurantEntityOptional.isPresent()) {
                RestaurantEntity stored = restaurantEntityOptional.get();
                if(!stored.getName().equals(restaurantEntity.getName())) {
                    throw new InvalidInputException("Restaurant name cannot be modified");
                }
                repository.save(restaurantEntity);
            } else {
                restaurantEntity.setBusinessId(hashids.encode(System.nanoTime()));
                repository.save(restaurantEntity);
            }
        } else {
            restaurantEntity.setBusinessId(hashids.encode(System.nanoTime()));
            repository.save(restaurantEntity);
        }

    }
}
