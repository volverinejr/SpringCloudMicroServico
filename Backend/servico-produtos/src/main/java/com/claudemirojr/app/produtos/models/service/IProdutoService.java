package com.claudemirojr.app.produtos.models.service;

import org.springframework.data.domain.Page;

import com.claudemirojr.app.produtos.models.ParamsRequestModel;
import com.claudemirojr.app.produtos.models.entity.Produto;

public interface IProdutoService {

	public Page<Produto> findAll(ParamsRequestModel prm);

	public Page<Produto> findByNomeContaining(String nome, ParamsRequestModel prm);

	public Produto FindById(Long id);

	public Produto insert(Produto produto);

	public Produto update(Produto produto);

	public void deleteById(Long id);

}
