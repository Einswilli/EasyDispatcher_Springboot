package com.ftf.easyfuturedispatch.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableAutoConfiguration
public class EasyfuturedispatchSecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(EasyfuturedispatchSecurityApplication.class, args);

	}

//
//	@Bean
//	CommandLineRunner start(AppUserService userService){
//   return args -> {
//	   userService.saveRole(new AppRole(null,"USER"));
//	  userService.saveRole(new AppRole(null,"ADMIN"));
//		 Stream.of("Admin").forEach(u->{
//		 	userService.saveUser(u,"9hconsultingservices@gmail.com","1234567","Charles","GRAND");
//  	 });
//
//	   userService.addRoleToUser("Admin","ADMIN");
//	 };
//
//	}
//
	@Bean
	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}




