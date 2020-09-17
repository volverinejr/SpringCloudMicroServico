package com.claudemirojr.app.items.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.app.items.models.Item;
import com.claudemirojr.app.items.models.Produto;
import com.claudemirojr.app.items.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

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
	

}
