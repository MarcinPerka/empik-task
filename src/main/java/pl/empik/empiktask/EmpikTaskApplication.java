package pl.empik.empiktask;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class EmpikTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmpikTaskApplication.class, args);
    }

}
