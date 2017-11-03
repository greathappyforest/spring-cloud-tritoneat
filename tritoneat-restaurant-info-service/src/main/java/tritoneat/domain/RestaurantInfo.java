package tritoneat.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonPropertyOrder({ "restaurantId","restaurantName","restaurantType", "restaurantPhoneNumber", "address", "city", "zip", "menu","hourOpen","hourClose", "checkOpen" })
public class RestaurantInfo {

    public enum RestaurantType{
        SEAFOOD, FASTFOOD, CHINESEFOOD,JAPANESEFOOD,STEAKHOUSE,MEXICAN,ITALIANFOOD,CAFE,DESSERT,OTHERS,UNKNOWN
    }
    @Id
    private String restaurantId;
    private String restaurantName;
    private RestaurantType restaurantType;
    private String restaurantPhoneNumber;
    private String address;
    private String city;
    private String zip;
    private List<Item> menu;
    private int hourOpen;
    private int hourClose;
    private boolean checkOpen;

    public RestaurantInfo(){this.menu=null;}

    @JsonCreator
    public RestaurantInfo(@JsonProperty("hourOpen") int hourOpen, @JsonProperty("hourClose") int hourClose){

        //        //check open
        this.hourOpen=hourOpen;
        this.hourClose=hourClose;

        LocalDateTime now = LocalDateTime.now();
        int currHour = now.getHour();
     //   System.out.println(currHour);
        if( currHour<this.hourOpen && currHour>this.hourClose){
            this.checkOpen=false;
        }
        else {
            this.checkOpen = true;
        }

    }

}
