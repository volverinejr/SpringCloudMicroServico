package com.claudemirojr.app.produtos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudemirojr.app.produtos.models.entity.Produto;
import com.claudemirojr.app.produtos.models.repository.ProdutoRepository;


@Service
public class ProdutoServiceImpl implements IProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Produto findById(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

}
