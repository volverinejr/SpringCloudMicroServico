package com.claudemirojr.app.items.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudemirojr.app.items.clientes.ProdutoClienteRest;
import com.claudemirojr.app.items.models.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements IItemService {

	@Autowired
	private ProdutoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		return new Item(clienteFeign.detalhe(id), quantidade);
	}

}
