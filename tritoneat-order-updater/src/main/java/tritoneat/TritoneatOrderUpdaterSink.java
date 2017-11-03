package tritoneat;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import tritoneat.domain.OrderInfo;

import java.io.IOException;

@EnableBinding(Sink.class)
@Slf4j
@MessageEndpoint
public class TritoneatOrderUpdaterSink {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void updateLocation(String input) throws IOException {
        log.info("Orderinfo input in updater " + input);
        OrderInfo orderInfo = this.objectMapper.readValue(input, OrderInfo.class);
        this.template.convertAndSend("/topic/orders", orderInfo);
    }

}
