package interview.parrot.questions.yelplike;

import io.prometheus.client.spring.web.EnablePrometheusTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @Author interviewparrot created on 02-Mar-2019
 */
@EntityScan(basePackageClasses = {YelpLikeServiceMain.class})
@SpringBootApplication
@EnableAutoConfiguration
@EnablePrometheusTiming
@ComponentScan(basePackages = "interview.parrot.questions.yelplike")
public class YelpLikeServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(YelpLikeServiceMain.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YelpLikeServiceMain.class);
    }

    @Autowired
    private Environment env;


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the type provided by Spring Boot:");

/*
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            */

        };
    }

}
