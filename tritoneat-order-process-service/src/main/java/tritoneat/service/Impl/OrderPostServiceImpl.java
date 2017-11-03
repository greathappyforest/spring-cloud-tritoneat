package tritoneat.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tritoneat.domain.OrderInfo;
import tritoneat.service.OrderPostService;


@Service
@Slf4j
public class OrderPostServiceImpl implements OrderPostService {



    @HystrixCommand(fallbackMethod = "postOrderInfoFallback")
    @Override
    public void postOrderInfo( OrderInfo orderInfo) {

        if(orderInfo.getIsOrderCreated()=="true") {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForLocation("http://127.0.0.1:8004/api/orders", orderInfo);
            log.info(String.format("Order Process service is callling distribution REST API"));
        }
        else{
            log.info(String.format("OrderId Fail to created"+ orderInfo.getOrderId()));
        }
    }

    public void postOrderInfoFallback(OrderInfo orderInfo) {
        log.error("Hystrix Fallback Method. Unable to send message for distribution.");
    }



}