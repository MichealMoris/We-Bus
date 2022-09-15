package com.genius.webus;

import com.genius.webus.entity.User;
import com.genius.webus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WeBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeBusApplication.class, args);
	}

}
