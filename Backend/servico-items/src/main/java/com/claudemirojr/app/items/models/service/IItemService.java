package com.claudemirojr.app.items.models.service;

import java.util.List;

import com.claudemirojr.app.items.models.Item;

public interface IItemService {
	
	public List<Item> findAll();
	
	public Item findById(Long id, Integer quantidade);

}
