package tritoneat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tritoneat.domain.RestaurantInfo;

import java.util.List;

public interface RestaurantInfoService {

    Page<RestaurantInfo> findByRestaurantType(RestaurantInfo.RestaurantType restaurantType, Pageable pageable);
    Page<RestaurantInfo> findByRestaurantId(String restaurantId, Pageable pageable);
    Page<RestaurantInfo> findByRestaurantName( String restaurantName, Pageable pageable);
    Page<RestaurantInfo> findAll( Pageable pageable);
    List<RestaurantInfo> saveRestaurantInfos(List<RestaurantInfo> restaurantInfos);

    void deleteAll();
}
