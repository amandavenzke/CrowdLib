package com.crowdlib.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.crowdlib.api.config.property.CrowdLibApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CrowdLibApiProperty.class)
public class CrowdlibApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrowdlibApiApplication.class, args);
	}

}
