package tritoneat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import tritoneat.domain.OrderInfo;

@Controller
@CrossOrigin(origins = "*")
public class WebSocketController {

    //relay
    @MessageMapping("/sendMessage")
    @SendTo("/topic/orders")
    public OrderInfo sendMessage(OrderInfo message){
        return message;
    }
}
