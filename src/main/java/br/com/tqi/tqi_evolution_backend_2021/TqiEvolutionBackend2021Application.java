package br.com.tqi.tqi_evolution_backend_2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TqiEvolutionBackend2021Application {

	public static void main(String[] args) {
		SpringApplication.run(TqiEvolutionBackend2021Application.class, args);
	}


}
