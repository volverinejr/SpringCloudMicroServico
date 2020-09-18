package com.claudemirojr.app.produtos.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.app.produtos.models.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findByNomeContaining(String nome, Pageable pageable);

}
