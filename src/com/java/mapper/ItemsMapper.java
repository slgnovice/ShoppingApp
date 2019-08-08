package com.java.mapper;

import java.util.List;

import com.java.pojo.Items;

public interface ItemsMapper {

	List<Items> findAlls();

	Items findOneItem(Integer id);

	void updateOne(Items items);
   
}