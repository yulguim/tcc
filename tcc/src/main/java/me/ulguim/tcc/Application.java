package me.ulguim.tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@EntityScan(basePackages = {"in.k2s.sdk.jpa.entity", "me.yulle.tcc.entity"})
@ComponentScan(basePackages = {"in.k2s.sdk.springboot"}, basePackageClasses = Application.class)
@EnableJpaRepositories(basePackages = {"in.k2s.sdk.springboot.service.base", "me.yulle.tcc.service"})

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
