package tritoneat;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tritoneat.domain.RestaurantInfo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tritoneat.domain.RestaurantInfo.RestaurantType.SEAFOOD;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TritoneatRestaurantInfoServiceApplication.class)
@WebIntegrationTest(randomPort = true)
public class restaurantInfoMvcTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

//
//    RestaurantInfo testRestaurantInfo = new RestaurantInfo("testRestaurantId","testRestaurantName",SEAFOOD,
//            "testRestaurantPhoneNumber", "testAddress",
//            "testCity", "testZip", null,6,20, false);
    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void whenVisitedGetAll_expectOk() throws Exception {
        this.mockMvc.perform(get("/restaurantinfo/get").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }


    @Test
    public void whenVisitedGetByName_expectOk() throws Exception {
        this.mockMvc.perform(get("/restaurantinfo/get/name/"+"testRestaurantName").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void whenVisitedGetByType_expectOk() throws Exception {
        this.mockMvc.perform(get("/restaurantinfo/get/type/"+"SEAFOOD").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void whenVisitedGetById_expectOk() throws Exception {
        this.mockMvc.perform(get("/restaurantinfo/get/id/"+"testRestaurantId").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}
