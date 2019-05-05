package interview.parrot.questions.yelplike.configuration;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author interviewparrot created on 18-Mar-2019
 */
@Configuration
public class ServiceConfiguration {


    @Bean
    public Hashids hashids() {
        return new Hashids("YelpLikeService", 8);
    }


}
