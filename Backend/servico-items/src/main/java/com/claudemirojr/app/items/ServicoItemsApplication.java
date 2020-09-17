package com.claudemirojr.app.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "servico-produtos")
public class ServicoItemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoItemsApplication.class, args);
	}

}
