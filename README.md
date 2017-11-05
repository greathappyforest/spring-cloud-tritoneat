# spring-cloud-tritoneat
## Introduction
A backend project design for online ordering platform.Customer are able to search and take order in specific restaurant. The backend side will handle order information and order process forward to the restaurant by message queue. Restaurant is able to grab success order information by websocket in the frontend web.
## Project Architecture
<p align="center">
  <img src="Document/Tritoneat Architecture.jpg"/>
</p>

## Get Started
### Running Infrastructure
- Install Docker 
  ``` 
  sudo apt-get install docker.io
  ```
- Start MongoDB and RabbitMQ by running ```docker-compose.yml``` under project root
  ```
  docker-compose up
  ```
### Build project
- Build each microservices under project root
  ```
  mvn clean install
  ```
### Running project
- Running following commond in different terminal for each eureka and hystrix-dashboard
  ```
  java -jar ./platform/eureka/target/eureka-0.0.1-SNAPSHOT.jar 
  java -jar ./platform/hystrix-dashboard/target/hystrix-dashboard-0.0.1-SNAPSHOT.jar 
  ```
- Running following commond in different terminal for each microservices
  ```
   java -jar ./tritoneat-user-info-service/target/tritoneat-user-info-service-1.0.0.BUILD-SNAPSHOT.jar 
   java -jar ./tritoneat-restaurant-info-service/target/tritoneat-restaurant-info-service-1.0.0.BUILD-SNAPSHOT.jar 
   java -jar ./tritoneat-order-process-service/target/tritoneat-order-process-service-1.0.0.BUILD-SNAPSHOT.jar 
   java -jar ./tritoneat-order-distribution/target/tritoneat-order-process-distrubution-1.0.0.BUILD-SNAPSHOT.jar 
   java -jar ./tritoneat-order-updater/target/tritoneat-order-updater-1.0.0.BUILD-SNAPSHOT.jar 
  ```
- Test Sample Data
  - RestauarntInfo data:  ```/tritoneat-restaurant-info-service/src/main/resources/restaurantInfo.json```  
     <a href="tritoneat-restaurant-info-service/src/main/resources/restaurantInfo.json">restaurantInfo.json</a>
  - UserInfo data: ```/tritoneat-user-info-service/src/main/resources/userInfo.json ```  
     <a href="tritoneat-user-info-service/src/main/resources/userInfo.json">userinfo.json</a>
  - OrderInfo data: ```/tritoneat-order-process-service/src/main/resources/orderInfo.json```  
    <a href="tritoneat-order-process-service/src/main/resources/orderInfo.json">orderinfo.json</a>  
- Note: Before post orderinfo, please post restaurantinfo and userinfo first.

## Web port GUI
  -  For Customer order food, visit http://localhost:8003
  -  For Restauarnt receive orders, visit http://localhost:8005

## Tritoneat Design Detail
 <a href="Document/Tritoneat Design Detail.pdf">Tritoneat Design Detail</a>