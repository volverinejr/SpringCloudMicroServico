package com.claudemirojr.app.items.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.claudemirojr.app.items.models.Item;


@Service
public class ItemServiceImpl implements IItemService {

	@Override
	public List<Item> findAll() {
		return null;
	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		return null;
	}

}
