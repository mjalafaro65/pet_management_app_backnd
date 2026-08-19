package com.maria.pet_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//adds config classes:
//	@Configuration->tags class as source of  all bean definitions
// 	@EnableAutoConfiguration->Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
// 	@ComponentScan->scans for other components, configs, and services in com/example pckg, letting it find controllers
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class PetManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetManagementAppApplication.class, args);
	}

}
