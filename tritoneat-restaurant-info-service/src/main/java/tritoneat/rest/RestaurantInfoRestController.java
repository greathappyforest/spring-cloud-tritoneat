package tritoneat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tritoneat.domain.RestaurantInfo;
import tritoneat.domain.RestaurantInfoRepository;
import tritoneat.service.RestaurantInfoService;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class RestaurantInfoRestController {


    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;


    private RestaurantInfoService restaurantInfoService;
    private RestaurantInfoRepository restaurantInfoRepository;

    @Autowired
    public RestaurantInfoRestController(RestaurantInfoService restaurantInfoService, RestaurantInfoRepository restaurantInfoRepository){
        this.restaurantInfoService=restaurantInfoService;
        this.restaurantInfoRepository=restaurantInfoRepository;
    }



    @RequestMapping(value = "/restaurantinfo/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<RestaurantInfo> upload(@RequestBody List<RestaurantInfo> restaurantInfos){
        return this.restaurantInfoService.saveRestaurantInfos(restaurantInfos);
    }

    @RequestMapping(value = "/restaurantinfo/delete", method = RequestMethod.DELETE)
    public void purge(){
        this.restaurantInfoService.deleteAll();
    }

    //GET by type
    @RequestMapping(value = "/restaurantinfo/get/type/{restaurantType}",method = RequestMethod.GET)
    public Page<RestaurantInfo> findByRestaurantType(@PathVariable RestaurantInfo.RestaurantType restaurantType, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.restaurantInfoService.findByRestaurantType(restaurantType, pageable);
    }

    @RequestMapping(value = "/restaurantinfo/get",method = RequestMethod.GET)
    public Page<RestaurantInfo> findAllRestaurantInfo(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.restaurantInfoService.findAll( pageable);
    }

    @RequestMapping(value = "/restaurantinfo/get/id/{restaurantId}",method = RequestMethod.GET)
    public  Page<RestaurantInfo> findByRestaurantId(@PathVariable String restaurantId,  @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.restaurantInfoService.findByRestaurantId(restaurantId, pageable);
    }

    //GET by Name
    @RequestMapping(value = "/restaurantinfo/get/name/{restaurantName}",method = RequestMethod.GET)
    public Page<RestaurantInfo> findByRestaurantName(
            @PathVariable String restaurantName, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.restaurantInfoService.findByRestaurantName(restaurantName, pageable);
    }


}
