package interview.parrot.questions.yelplike.service;

import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import interview.parrot.questions.yelplike.dto.Restaurant;
import interview.parrot.questions.yelplike.entity.HoursOfOperation;
import interview.parrot.questions.yelplike.entity.RestaurantEntity;
import interview.parrot.questions.yelplike.exception.InvalidInputException;
import interview.parrot.questions.yelplike.repository.RestaurantRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author interviewparrot created on 25-Mar-2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantServiceTest {


    @Autowired
    private RestaurantRepository repository;

    @Autowired
    RestaurantService restaurantService;

    private RestaurantEntity expectedRestaurant;

    @Before
    public void setup() throws InvalidInputException {

        expectedRestaurant = new RestaurantEntity();
        expectedRestaurant.setName("Test Thai Place");
        expectedRestaurant.setGoodForKids(true);
        expectedRestaurant.setCity("Toronto");
        expectedRestaurant.setCity("Canada");
        expectedRestaurant.setAddress("1212, Some Street");
        expectedRestaurant.setBusinessId("o2fXhV");

        restaurantService.saveOrUpdate(expectedRestaurant);
    }


    @Test
    public void testGetRestaurant() throws InvalidInputException {
        RestaurantEntity actualEntity = restaurantService.get("Test Thai Place");
        Assert.assertNotNull(actualEntity);
        Assert.assertEquals(expectedRestaurant.getBusinessId(), actualEntity.getBusinessId());
        Assert.assertEquals(expectedRestaurant.getName(), actualEntity.getName());
    }

    @Test
    public void testCachePutInCache() throws InvalidInputException {
        LoadingCache loadingCache = restaurantService.getLoadingCache();
        RestaurantEntity actualEntity = restaurantService.get("Test Thai Place");
        RestaurantEntity cachedEntity = (RestaurantEntity) loadingCache.getIfPresent(actualEntity.getName());
        Assert.assertNotNull(cachedEntity);
        Assert.assertEquals(actualEntity.getBusinessId(), cachedEntity.getBusinessId());
    }

    @Test
    public void testInvalidateCache() throws InvalidInputException {
        LoadingCache loadingCache = restaurantService.getLoadingCache();
        RestaurantEntity actualEntity = restaurantService.get("Test Thai Place");
        RestaurantEntity cachedEntity = (RestaurantEntity) loadingCache.getIfPresent(actualEntity.getName());
        Assert.assertNotNull(cachedEntity);
        Assert.assertEquals(actualEntity.getBusinessId(), cachedEntity.getBusinessId());
        HoursOfOperation hoursOfOperation = new HoursOfOperation();
        hoursOfOperation.setDayOfWeek("Monday");
        hoursOfOperation.setHoursOfOperation("9am to 5pm");
        actualEntity.setOperationHours(Lists.newArrayList(hoursOfOperation));
        restaurantService.saveOrUpdate(actualEntity);
        Assert.assertEquals(0, loadingCache.size());
    }


}
