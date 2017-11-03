package tritoneat.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tritoneat.domain.RestaurantInfo;
import tritoneat.domain.RestaurantInfoRepository;
import tritoneat.service.RestaurantInfoService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantInfoServiceImpl implements RestaurantInfoService {

    private RestaurantInfoRepository restaurantInfoRepository;
    @Autowired
    public RestaurantInfoServiceImpl(RestaurantInfoRepository restaurantInfoRepository){
        this.restaurantInfoRepository=restaurantInfoRepository;
    }


    @Override
    public List<RestaurantInfo> saveRestaurantInfos(List<RestaurantInfo> restaurantInfos) {
        return (ArrayList<RestaurantInfo>)restaurantInfoRepository.save(restaurantInfos);
    }

    @Override
    public Page<RestaurantInfo> findByRestaurantType(RestaurantInfo.RestaurantType restaurantType, Pageable pageable) {
        return restaurantInfoRepository.findByRestaurantType(restaurantType, pageable);
    }
    @Override
    public Page<RestaurantInfo> findByRestaurantId(String restaurantId, Pageable pageable) {

        return restaurantInfoRepository.findByRestaurantId(restaurantId,pageable);
    }
    @Override
    public Page<RestaurantInfo> findByRestaurantName(String restaurantName, Pageable pageable) {
        return restaurantInfoRepository.findByRestaurantName(restaurantName,pageable);
    }

    @Override
    public Page<RestaurantInfo> findAll(Pageable pageable) {
        return restaurantInfoRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        restaurantInfoRepository.deleteAll();
    }


}
