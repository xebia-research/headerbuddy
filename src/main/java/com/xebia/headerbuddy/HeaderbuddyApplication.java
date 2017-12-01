package com.xebia.headerbuddy;

import com.xebia.headerbuddy.models.dbentities.User;
import com.xebia.headerbuddy.models.dbentities.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HeaderbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeaderbuddyApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repo){
		return (args) -> {
			repo.save(new User("apikeymemes", "email@email.com"));
			repo.save(new User("apikey2", "email2@email.com"));

			System.out.println(	"-- ALL USERS --");
			for(User user : repo.getAllUsers()){
				System.out.println(user.toString());
			}
		};
	}
}
