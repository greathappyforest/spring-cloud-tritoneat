package tritoneat.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tritoneat.domain.UserInfo;
import tritoneat.domain.UserInfoRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserInfoRestController {


    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 5;

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoRestController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }


    @RequestMapping(value = "/userinfo/post", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<UserInfo> userInfos){
        this.userInfoRepository.save(userInfos);
    }


    @RequestMapping(value = "/userinfo/delete", method = RequestMethod.DELETE)
    public void purge(){
        this.userInfoRepository.deleteAll();
    }


    @RequestMapping(value = "/userinfo/get",method = RequestMethod.GET)
    public Page<UserInfo> findAll(
            @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.userInfoRepository.findAll( pageable);
    }

    //GET by Name
    @RequestMapping(value = "/userinfo/get/name/{userName}",method = RequestMethod.GET)
    public Page<UserInfo> findByRestaurantName(
            @PathVariable String userName, @PageableDefault(page = DEFAULT_PAGE_NUMBER, size = DEFAULT_PAGE_SIZE) Pageable pageable){
        return this.userInfoRepository.findByUserName(userName, pageable);
    }
}
