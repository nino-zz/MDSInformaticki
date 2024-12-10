package nino.rs.mdsinformaticki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nino.rs.mdsinformaticki")
public class MdsInformatickiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdsInformatickiApplication.class, args);
    }

}
