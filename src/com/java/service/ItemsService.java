package com.java.service;

import java.util.List;

import com.java.pojo.Items;

public interface ItemsService {
	
	public List<Items> findAll();

	public Items  findOne(Integer id);

	public void updateItems(Items items);
}
