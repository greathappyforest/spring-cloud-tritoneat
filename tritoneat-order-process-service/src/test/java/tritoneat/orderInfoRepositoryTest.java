package tritoneat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tritoneat.domain.OrderInfo;
import tritoneat.domain.OrderInfoRepository;
import tritoneat.domain.RestaurantInfoRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TritoneatRestaurantInfoServiceApplication.class)
@WebAppConfiguration
public class orderInfoRepositoryTest {
    @Autowired
    private OrderInfoRepository orderInfoRepository;





    OrderInfo testOrderInfo1 = generateOrderInfo("testOrderId1", "testuser1", "testRestaurant1");
    OrderInfo testOrderInfo2 = generateOrderInfo("testOrderId2", "testuser2", "testRestaurant2");
    OrderInfo testOrderInfo3 = generateOrderInfo("testOrderId3", "testuser3", "testRestaurant3");



    @Test
    public void whenfindByRestaurantId_expectOk(){

        this.orderInfoRepository.save(testOrderInfo1);
        this.orderInfoRepository.save(testOrderInfo2);
        this.orderInfoRepository.save(testOrderInfo3);
        assertThat(this.orderInfoRepository.findAll(new PageRequest(0,100)).getContent().get(0).getOrderId()).isEqualTo("testOrderId1");
        assertThat(this.orderInfoRepository.findAll(new PageRequest(0,100)).getContent().get(1).getOrderId()).isEqualTo("testOrderId2");
        assertThat(this.orderInfoRepository.findAll(new PageRequest(0,100)).getContent().get(2).getOrderId()).isEqualTo("testOrderId3");
    }


    private OrderInfo generateOrderInfo(String orderId, String userName, String restaurantName){
        OrderInfo orderInfo =new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setRestaurantName(restaurantName);
        orderInfo.setUserName(userName);

        return orderInfo;
    }
}
