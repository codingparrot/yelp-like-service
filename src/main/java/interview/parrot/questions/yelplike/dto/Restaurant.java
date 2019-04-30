package interview.parrot.questions.yelplike.dto;

import interview.parrot.questions.yelplike.entity.ParkingType;
import lombok.Data;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
@Data
public class Restaurant {
    private String businessId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private double stars;
    private int reviewCount;
    private boolean isGoodForKids;
    private boolean reservationRequired;
    private ParkingType parking;
}
