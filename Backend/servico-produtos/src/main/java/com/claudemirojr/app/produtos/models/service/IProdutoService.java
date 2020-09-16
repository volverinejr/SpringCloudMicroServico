package com.claudemirojr.app.produtos.models.service;

import java.util.List;

import com.claudemirojr.app.produtos.models.entity.Produto;

public interface IProdutoService {

	public List<Produto> findAll();
	
	public Produto findById(Long id);
	
	
}
