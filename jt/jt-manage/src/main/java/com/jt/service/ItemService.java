package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {

	EasyUITable queryItems(Integer page, Integer rows);

	void saveItem(Item item, ItemDesc itemDesc);

	void deleteItemById(Long[] ids);

	void changeStatus(int status, Long[] ids);

	void updateItem(Item item, ItemDesc itemDesc);

}
