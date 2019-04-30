package interview.parrot.questions.yelplike.controller;

import interview.parrot.questions.yelplike.dto.Response;
import interview.parrot.questions.yelplike.dto.Restaurant;
import interview.parrot.questions.yelplike.dto.ResponseStatus;
import interview.parrot.questions.yelplike.entity.RestaurantEntity;
import interview.parrot.questions.yelplike.exception.InvalidInputException;
import interview.parrot.questions.yelplike.service.RestaurantService;
import interview.parrot.questions.yelplike.util.TransformationFunctions;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static interview.parrot.questions.yelplike.util.TransformationFunctions.*;

/**
 * Controller which serves as the Rest api's for the restaurants.
 * Provides basic CRUD api's
 * @Author interviewparrot created on 03-Mar-2019
 */
@RestController
@Log4j2
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    /**
     * Get a restaurant detail given a name of the restaurant.
     * @return
     */
    @RequestMapping("/restaurant/{name}")
    public Restaurant getRestaurant(@PathVariable("name") String name) throws InvalidInputException {
        log.info("Get Restaurant: {}", name);
        return restaurantFunction.apply(restaurantService.get(name));
    }


    /**
     * List all the restaurents in the database.
     * Only used for testing purpose.
     * @return
     */
    @RequestMapping("/restaurants")
    public List<Restaurant> listRestaurent() throws InvalidInputException {
        List<Restaurant> response = new ArrayList<>();
        List<RestaurantEntity> list = restaurantService.list();
        if(list != null && !list.isEmpty()) {
            list.forEach( restaurantEntity -> {
                response.add(restaurantFunction.apply(restaurantEntity));
            });
        }

        return response;
    }



    /**
     * Post a new Restaurant detail or update an existing Restaurant.
     * @param restaurant
     * @return
     */
    @RequestMapping(value = "/restaurant", method = RequestMethod.POST,
            consumes = {"application/json"}, produces = {"application/json"})
    public Restaurant createOrUpdateRestaurant(@RequestBody Restaurant restaurant) throws InvalidInputException {
        Validate.notBlank(restaurant.getName());
        Validate.notBlank(restaurant.getAddress());
        Validate.notBlank(restaurant.getCity());
        Validate.notBlank(restaurant.getCountry());
        log.info("CreateOrUpdate Restauran: {}", restaurant);
        restaurantService.saveOrUpdate(restaurantEntityFunction.apply(restaurant));
        return restaurant;
    }


    /**
     * Upload photos about the restaurant.
     * @param content
     * @param encoding
     * @return
     */
    @RequestMapping(value="/restaurant/{name}/photos", method = RequestMethod.POST,
            consumes = {"application/json"}, produces = {"application/json"})
    public Response uploadPhotos(@RequestParam("content") String content,
                                 @RequestParam("encoding") String encoding) {

        //TODO: Add your logic to upload the photos.

        return Response.builder().status(ResponseStatus.OK).build();
    }

}
