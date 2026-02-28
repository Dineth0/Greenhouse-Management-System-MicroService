package lk.ijse.serviceregisry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceregisryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceregisryApplication.class, args);
    }

}
