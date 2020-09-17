package com.claudemirojr.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicoItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoItemsApplication.class, args);
	}

}
