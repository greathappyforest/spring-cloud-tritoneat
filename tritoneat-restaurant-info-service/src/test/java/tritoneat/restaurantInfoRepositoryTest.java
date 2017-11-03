package tritoneat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tritoneat.domain.Item;
import tritoneat.domain.RestaurantInfo;
import tritoneat.domain.RestaurantInfoRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static tritoneat.domain.RestaurantInfo.RestaurantType.SEAFOOD;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TritoneatRestaurantInfoServiceApplication.class)
@WebAppConfiguration
public class restaurantInfoRepositoryTest {
    @Autowired
    private RestaurantInfoRepository restaurantInfoRepository;


    RestaurantInfo testRestaurantInfo = new RestaurantInfo("testRestaurantId","testRestaurantName",SEAFOOD,
                "testRestaurantPhoneNumber", "testAddress",
                        "testCity", "testZip", null,6,20, false);
    @Test
    public void whenfindByRestaurantId_expectOk(){
        this.restaurantInfoRepository.save(testRestaurantInfo);
        assertThat(this.restaurantInfoRepository.findByRestaurantId("testRestaurantId", new PageRequest(0,1)).getContent().get(0).getRestaurantId()).isEqualTo("testRestaurantId");
    }
    @Test
    public void whenfindByRestaurantType_expectOk(){
        this.restaurantInfoRepository.save(testRestaurantInfo);
        assertThat(this.restaurantInfoRepository.findByRestaurantType(SEAFOOD, new PageRequest(0,1)).getContent().get(0).getRestaurantType()).isEqualTo(SEAFOOD);
    }
    @Test
    public void whenfindByRestaurantName_expectOk(){
        this.restaurantInfoRepository.save(testRestaurantInfo);
        assertThat(this.restaurantInfoRepository.findByRestaurantName("testRestaurantName", new PageRequest(0,1)).getContent().get(0).getRestaurantName()).isEqualTo("testRestaurantName");
    }

}
