package interview.parrot.questions.yelplike.util;

import interview.parrot.questions.yelplike.dto.Restaurant;
import interview.parrot.questions.yelplike.entity.RestaurantEntity;

import java.util.function.Function;

/**
 * Holds all the transformation function from entities to data transfer objects and vice versa.
 *
 * @Author interviewparrot created on 18-Mar-2019
 */
public class TransformationFunctions {

    public static Function<Restaurant, RestaurantEntity> restaurantEntityFunction = new Function<Restaurant, RestaurantEntity>() {
        @Override
        public RestaurantEntity apply(Restaurant restaurant) {
            RestaurantEntity entity = new RestaurantEntity();
            entity.setName(restaurant.getName());
            entity.setBusinessId(restaurant.getBusinessId());
            entity.setAddress(restaurant.getAddress());
            entity.setCity(restaurant.getCity());
            entity.setPostalCode(restaurant.getPostalCode());
            entity.setPrimaryParking(restaurant.getParking());
            entity.setGoodForKids(restaurant.isGoodForKids());
            return entity;
        }
    };

    public static Function<RestaurantEntity, Restaurant> restaurantFunction = new Function<RestaurantEntity, Restaurant>() {
        @Override
        public Restaurant apply(RestaurantEntity restaurantEntity) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantEntity.getName());
            restaurant.setBusinessId(restaurantEntity.getBusinessId());
            restaurant.setAddress(restaurantEntity.getAddress());
            restaurant.setCity(restaurantEntity.getCity());
            restaurant.setPostalCode(restaurantEntity.getPostalCode());
            restaurant.setParking(restaurantEntity.getPrimaryParking());
            restaurant.setGoodForKids(restaurantEntity.isGoodForKids());
            return restaurant;
        }
    };
}
