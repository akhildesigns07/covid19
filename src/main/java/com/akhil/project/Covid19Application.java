package com.akhil.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.akhil.project.model.User;
import com.akhil.project.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
public class Covid19Application extends SpringBootServletInitializer  {
	
	@Autowired
	 UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
		
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Covid19Application.class);
    }
	public void run(String... args) throws Exception {
		//userRepository.save(new User ("akhil","akhiluser","email@rmail.com","password"));
		
	}

}
