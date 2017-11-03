package tritoneat.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import javafx.scene.control.PasswordField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.security.util.Password;

import java.security.KeyStore;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@JsonPropertyOrder({"userId","userName", "userAddress", "userPhoneNumber", "userEmail"})
public class UserInfo {
    @Id
    private String userId;
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
    private String userEmail;



}
