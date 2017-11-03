package tritoneat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tritoneat.domain.OrderInfo;
import tritoneat.domain.OrderInfoRepository;
import tritoneat.domain.RestaurantInfo;
import tritoneat.domain.UserInfo;
import tritoneat.service.OrderPostService;

@RestController
@CrossOrigin(origins = "*")
public class OrderProcessRestController {

        private static final int DEFAULT_PAGE_NUMBER = 0;
        private static final int DEFAULT_PAGE_SIZE = 5;

    private final OrderInfoRepository orderInfoRepository;
    private final OrderPostService orderPostService;
    private UserInfo userInfo;
    private RestaurantInfo restaurantInfo;

    @Autowired
    public OrderProcessRestController(OrderInfoRepository orderInfoRepository, OrderPostService orderPostService) {
        this.orderInfoRepository = orderInfoRepository;
        this.orderPostService = orderPostService;
    }

    @RequestMapping(value = "orderinfo/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody OrderInfo orderInfo){
        this.orderInfoRepository.save(orderInfo);
        this.orderPostService.postOrderInfo(orderInfo);

    }


    @RequestMapping(value = "orderinfo/get",method = RequestMethod.GET)
    public Page<OrderInfo> findAll(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.orderInfoRepository.findAll( pageable);
    }


    @RequestMapping(value = "/orderinfo/delete", method = RequestMethod.DELETE)
    public void purge(){
        this.orderInfoRepository.deleteAll();
    }



}
