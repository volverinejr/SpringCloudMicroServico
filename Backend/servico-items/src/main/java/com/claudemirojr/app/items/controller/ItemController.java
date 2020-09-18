package com.claudemirojr.app.items.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.app.items.models.Item;
import com.claudemirojr.app.items.models.Produto;
import com.claudemirojr.app.items.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	
	@Autowired
	private Environment env;
	
	@Value("${configuracao.texto}")
	private String texto;
	

	@Autowired
	@Qualifier("serviceFeign")
	private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver/{id}/quantidade/{quantidade}")
	public Item detalhe(@PathVariable Long id, @PathVariable Integer quantidade) {
		return itemService.findById(id, quantidade);
	}
	
	
	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer quantidade) {
		Produto produto = new Produto();
		
		produto.setId(0L);
		produto.setNome("Produto Alternativo");
		produto.setPreco(1.30);
		produto.setCriadoEm( new Date() );
		
		return new Item(produto, 1);
	}
	
	
	@GetMapping("/obter-config")
	public ResponseEntity<?> obterConfig() {
		Map<String, String> json = new HashMap<>();
		
		json.put("texto", texto);
		
		if ( env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev") ) {
			json.put("Autor", env.getProperty("configuracao.autor.name"));
			json.put("Email", env.getProperty("configuracao.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	

}
