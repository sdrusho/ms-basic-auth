package org.basic.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.basic.microservice")
public class MsBasicAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBasicAuthApplication.class, args);
    }

}
