package interview.parrot.questions.yelplike.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import interview.parrot.questions.yelplike.dto.Restaurant;
import interview.parrot.questions.yelplike.entity.RestaurantEntity;
import interview.parrot.questions.yelplike.exception.InvalidInputException;
import interview.parrot.questions.yelplike.repository.RestaurantRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
import lombok.extern.log4j.Log4j2;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
@Service
@Log4j2
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private Hashids hashids;

    private LoadingCache<String, RestaurantEntity> cache;

    public RestaurantService(MeterRegistry registry) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, RestaurantEntity>() {
                            public RestaurantEntity load(String name) throws InvalidInputException {
                                List<RestaurantEntity> restaurantEntities = repository.findByName(name);
                                if(restaurantEntities == null || restaurantEntities.isEmpty()) {
                                    throw new InvalidInputException("Restaurant name doesn't exists");
                                }
                                return repository.findByName(name).get(0);
                            }
                        });

        GuavaCacheMetrics guavaCacheMetrics = new GuavaCacheMetrics(cache, "restaurantcache",
                Collections.singleton(Tag.of("cachename", "RestaurantCache")));
        guavaCacheMetrics.bindTo(registry);
    }

    @Timed
    public RestaurantEntity get(String name) throws InvalidInputException {
        try {
            return cache.get(name);
        } catch (ExecutionException e) {
           log.error(e);
           return null;
        }
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

                // invalidate the cache entry on update
                cache.invalidate(restaurantEntity.getName());
            } else {
                restaurantEntity.setBusinessId(hashids.encode(System.nanoTime()));
                repository.save(restaurantEntity);
            }
        } else {
            restaurantEntity.setBusinessId(hashids.encode(System.nanoTime()));
            repository.save(restaurantEntity);
        }

    }

    protected LoadingCache getLoadingCache() {
        return this.cache;
    }



}
