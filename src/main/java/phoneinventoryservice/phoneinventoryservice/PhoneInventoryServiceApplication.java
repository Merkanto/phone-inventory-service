package phoneinventoryservice.phoneinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class PhoneInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneInventoryServiceApplication.class, args);
    }

}
