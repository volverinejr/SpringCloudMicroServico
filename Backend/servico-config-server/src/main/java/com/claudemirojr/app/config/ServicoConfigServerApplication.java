package com.claudemirojr.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ServicoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoConfigServerApplication.class, args);
	}

}
