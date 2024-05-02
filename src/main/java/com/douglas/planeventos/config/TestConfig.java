package com.douglas.planeventos.config;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import com.douglas.planeventos.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@PostConstruct
	public void instanciaDB() {
		this.dbService.startDB();
	}
}
