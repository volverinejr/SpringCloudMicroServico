package com.claudemirojr.app.produtos.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.app.produtos.models.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
