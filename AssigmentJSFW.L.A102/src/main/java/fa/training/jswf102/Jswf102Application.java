package fa.training.jswf102;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Jswf102Application {


    public static void main(String[] args) {

        SpringApplication.run(Jswf102Application.class, args);

    }

}
