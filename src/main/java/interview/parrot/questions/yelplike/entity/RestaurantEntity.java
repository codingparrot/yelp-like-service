package interview.parrot.questions.yelplike.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Defines the properties of a Restaurant.
 * @Author interviewparrot created on 02-Mar-2019
 */
@Entity
@Data
@ToString
@Table(name="restaurant")
public class RestaurantEntity extends BaseModifiableEntity {


    @Id
    private String businessId;

    @Column(unique=true)
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private double latitude;
    private double longitude;
    private double stars;
    private int reviewCount;
    private boolean isGoodForKids;
    private boolean reservationRequired;

    @Enumerated(EnumType.STRING)
    private OperationStatus operationStatus;
    private ParkingType primaryParking;
    private ParkingType parking2;

    @Column(name="operationHours", length = 2550)
    @Convert(converter = ListConverterJson.class)
    private List<HoursOfOperation> operationHours;


}
