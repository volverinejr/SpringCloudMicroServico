package com.claudemirojr.app.produtos.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudemirojr.app.produtos.exception.NotFoundException;
import com.claudemirojr.app.produtos.models.ParamsRequestModel;
import com.claudemirojr.app.produtos.models.entity.Produto;
import com.claudemirojr.app.produtos.models.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements IProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<Produto> findAll(ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		return produtoRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Produto> findByNomeContaining(String nome, ParamsRequestModel prm) {
		Pageable pageable = prm.toSpringPageRequest();

		return produtoRepository.findByNomeContaining(nome, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Produto FindById(Long id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format("Registro não encontrado para id %d", id)));
	}

	@Override
	@Transactional
	public Produto insert(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	@Transactional
	public Produto update(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		produtoRepository.deleteById(id);
	}

}
