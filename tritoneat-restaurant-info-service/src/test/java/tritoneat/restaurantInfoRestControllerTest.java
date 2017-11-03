package tritoneat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import tritoneat.domain.RestaurantInfo;
import tritoneat.domain.RestaurantInfoRepository;
import tritoneat.rest.RestaurantInfoRestController;
import tritoneat.service.RestaurantInfoService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tritoneat.domain.RestaurantInfo.RestaurantType.*;

public class restaurantInfoRestControllerTest {
    private RestaurantInfoRepository repository;
    private RestaurantInfoService service;
    private RestaurantInfoRestController controller;
    private List<RestaurantInfo> inputRestaurantInfos;

    @Before
    public void setupMock(){
        repository=mock(RestaurantInfoRepository.class);
        service=mock(RestaurantInfoService.class);
        controller =new RestaurantInfoRestController(service,repository);
        inputRestaurantInfos=new ArrayList<>();
        inputRestaurantInfos.add(generateRestaurantInfo("testRestaurant1",SEAFOOD,"92001"));
        inputRestaurantInfos.add(generateRestaurantInfo("testRestaurant2",CHINESEFOOD,"92002"));
        inputRestaurantInfos.add(generateRestaurantInfo("testRestaurant3",JAPANESEFOOD,"92003"));
    }


    @Test
    public void whenUpload_expectSuccess(){
        List<RestaurantInfo> restaurantInfos =new ArrayList<>();
        restaurantInfos.add(generateRestaurantInfo("testRestaurant1",SEAFOOD,"92001"));
        restaurantInfos.add(generateRestaurantInfo("testRestaurant2",CHINESEFOOD,"92002"));
        when(service.saveRestaurantInfos(inputRestaurantInfos)).thenReturn(restaurantInfos);

        //assertj
        assertThat(controller.upload(inputRestaurantInfos)).size().isEqualTo(2);
        assertThat(controller.upload(inputRestaurantInfos).get(0).getZip()).isEqualTo("92001");
        assertThat(controller.upload(inputRestaurantInfos).get(1).getRestaurantType()).isEqualTo(CHINESEFOOD);


    }



    private RestaurantInfo generateRestaurantInfo(String restaurantName, RestaurantInfo.RestaurantType restaurantType, String zip){
        RestaurantInfo restaurantInfo =new RestaurantInfo();
        restaurantInfo.setRestaurantName(restaurantName);
        restaurantInfo.setRestaurantType(restaurantType);
        restaurantInfo.setZip(zip);
        return restaurantInfo;
    }
}
