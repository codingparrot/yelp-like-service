package interview.parrot.questions.yelplike.dataloader;

import interview.parrot.questions.yelplike.controller.RestaurantController;
import interview.parrot.questions.yelplike.dto.Restaurant;
import interview.parrot.questions.yelplike.exception.InvalidInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @Author interviewparrot created on 27-Apr-2019
 */
@Service
@Log4j2
public class SampleDataLoader {

    @Autowired
    RestaurantController restaurantController;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Awesome Thai");
        restaurant1.setAddress("1080 Yonge St");
        restaurant1.setCity("Toronto");
        restaurant1.setCountry("Canada");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("The burger king");
        restaurant2.setAddress("707 St");
        restaurant2.setCity("Toronto");
        restaurant2.setCountry("Canada");
        try {
            restaurantController.createOrUpdateRestaurant(restaurant1);
            restaurantController.createOrUpdateRestaurant(restaurant2);
        } catch (InvalidInputException e) {
            log.error("Unable to save sample data");
        }
    }
}
