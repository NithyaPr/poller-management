package se.nithya.poller.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PollerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollerManagementApplication.class, args);
	}

}
