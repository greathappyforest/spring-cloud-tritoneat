package tritoneat;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class TritoneatOrderUpdaterApplication {
    public static void main(String [] args) throws Exception{
        SpringApplication.run(TritoneatOrderUpdaterApplication.class);
    }
}
