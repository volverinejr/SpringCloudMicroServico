package com.claudemirojr.app.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.app.items.models.Item;
import com.claudemirojr.app.items.models.service.IItemService;

@RestController
public class ItemController {

	@Autowired
	private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}

	@GetMapping("/ver/{id}/quantidade/{quantidade}")
	public Item detalhe(@PathVariable Long id, @PathVariable Integer quantidade) {
		return itemService.findById(id, quantidade);
	}

}
