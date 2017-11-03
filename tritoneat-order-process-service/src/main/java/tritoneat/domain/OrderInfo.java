package tritoneat.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.util.JSON;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Document
@JsonPropertyOrder({ "orderId", "userName", "userAddress", "uerPhoneNumber","useEmail", "restaurantName", "restaurantAddress","restaurantPhoneNumber","restaurantIsOpen","orderItems","orderAmount","orderMethod","checkpayment","isOrderCreated","timeStamp" })
public class OrderInfo {
    @Id
    private String orderId;
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
    private String userEmail;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantPhoneNumber;
    private boolean restaurantCheckOpen;
    private List<Item> orderItems;
    private double orderAmount;
    private String orderMethod;
    //payment will sent to third party processing, here simulate 70% payment success rate
    private boolean checkpayment;
    private String isOrderCreated;
    private String timeStamp;

    @JsonCreator
    public OrderInfo(@JsonProperty("restaurantName") String restaurantName, @JsonProperty("userName") String userName,@JsonProperty("orderItems") List<Item> orderItems){
        //cal amount
        this.restaurantName=restaurantName;
        this.userName=userName;
        this.orderItems=orderItems;
        double sum=0.0;
        for(Item item : orderItems){
            sum+=item.getItemPrice()*item.getNumsOfItem();
        }
        this.orderAmount=sum;

        // generator payment
        int range = (10 - 1) + 1;
        int res= (int)((Math.random()*range)+1)/3;
        if(res>0)
            this.checkpayment=true;
        else
            this.checkpayment=false;


        //parse useinfo from get 8001 api
        String userInfoUri = "http://127.0.0.1:8001/userinfo/get/name/"+userName;
        //System.out.println(userInfoUri);

        RestTemplate restTemplate = new RestTemplate();
        Map<String,ArrayList<Map<String,String>>> userResult = restTemplate.getForObject(userInfoUri, Map.class);

        this.userName=userResult.get("content").get(0).get("userName");
        this.userAddress=userResult.get("content").get(0).get("userAddress");
        this.userEmail=userResult.get("content").get(0).get("userEmail");
        this.userPhoneNumber=userResult.get("content").get(0).get("userPhoneNumber");

        //parse restaurantinfo from get 8002 api
        String restaurantInfoUri = "http://127.0.0.1:8002/restaurantinfo/get/name/"+restaurantName;
        System.out.println(restaurantInfoUri);

        Map<String,ArrayList<Map<String,String>>> restaurantResult = restTemplate.getForObject(restaurantInfoUri, Map.class);
        this.restaurantName=restaurantResult.get("content").get(0).get("restaurantName");
        this.restaurantAddress=restaurantResult.get("content").get(0).get("restaurantAddress");
        this.restaurantPhoneNumber=restaurantResult.get("content").get(0).get("restaurantPhoneNumber");
        Map<String,ArrayList<Map<String,Boolean>>> restaurantBooleanResult = restTemplate.getForObject(restaurantInfoUri, Map.class);
        this.restaurantCheckOpen =restaurantBooleanResult.get("content").get(0).get("checkOpen");

        //timeStamp
        this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        //check if ordercreated
        if(this.checkpayment && this.restaurantCheckOpen)
            this.isOrderCreated="true";
        else
            this.isOrderCreated="false";



    }




}
