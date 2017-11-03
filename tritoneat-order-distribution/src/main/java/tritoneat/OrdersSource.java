package tritoneat;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@EnableBinding(Source.class)
@Component
public class OrdersSource {

    @Autowired
    private MessageChannel output;

    @RequestMapping(value = "/api/orders", method = RequestMethod.POST)
    public void orders(@RequestBody String orderInfo){

        this.output.send(MessageBuilder.withPayload(orderInfo).build());
        log.info("ordersInfo output in distribution " + orderInfo);
    }

}
