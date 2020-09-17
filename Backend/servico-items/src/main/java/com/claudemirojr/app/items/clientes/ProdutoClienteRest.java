package com.claudemirojr.app.items.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.claudemirojr.app.items.models.Produto;

@FeignClient(name = "servico-produtos", url = "localhost:8001")
public interface ProdutoClienteRest {

	@GetMapping("/listar")
	public List<Produto> listar();

	@GetMapping("/ver/{id}")
	public Produto detalhe(@PathVariable Long id);

}
