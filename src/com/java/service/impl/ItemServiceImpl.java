package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.mapper.ItemsMapper;
import com.java.pojo.Items;
import com.java.service.ItemsService;
@Service
public class ItemServiceImpl implements ItemsService {
	
	@Autowired(required = true)
    ItemsMapper itemsMapper;

	@Override
	public List<Items> findAll() {
		return itemsMapper.findAlls();
	}

	@Override
	public Items findOne(Integer id) {
		
		return itemsMapper.findOneItem(id);
	}

	@Override
	public void updateItems(Items items) {
		itemsMapper.updateOne(items);
		
	}

}
