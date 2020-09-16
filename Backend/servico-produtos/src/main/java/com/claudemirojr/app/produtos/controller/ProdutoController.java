package com.claudemirojr.app.produtos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.claudemirojr.app.produtos.models.entity.Produto;
import com.claudemirojr.app.produtos.models.service.IProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private IProdutoService produtoService;

	@GetMapping("/listar")
	public List<Produto> listar() {
		return produtoService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Produto detalhe(@PathVariable Long id) {
		return produtoService.findById(id);
	}

}
