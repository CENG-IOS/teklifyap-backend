package edu.eskisehir.teklifyap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TeklifyapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeklifyapApplication.class, args);
	}

}
