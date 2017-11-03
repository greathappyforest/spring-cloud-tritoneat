package tritoneat.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static tritoneat.domain.RestaurantInfo.RestaurantType;

public interface RestaurantInfoRepository extends PagingAndSortingRepository<RestaurantInfo, String> {
    Page<RestaurantInfo> findByRestaurantType(@Param("restaurantType") RestaurantType restaurantType, Pageable pageable);
    Page<RestaurantInfo> findByRestaurantId(@Param("restaurantId") String restaurantId, Pageable pageable);
    Page<RestaurantInfo> findByRestaurantName(@Param("restaurantName") String restaurantName, Pageable pageable);
}