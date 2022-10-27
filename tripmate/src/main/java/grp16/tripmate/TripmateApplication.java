package grp16.tripmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "grp16.tripmate.login")
public class TripmateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripmateApplication.class, args);
	}

}
