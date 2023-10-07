package com.example.demo;

import com.example.demo.domain.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(
		{FileUploadProperties.class}
)
@SpringBootApplication
public class PersonalcolorApplication {

	public static void main(String[] args) {

		SpringApplication.run(PersonalcolorApplication.class, args);
	}

}
