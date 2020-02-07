package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.ItemDesc;

@Service
public class ItemDescServiceImpl implements ItemDescService {
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public ItemDesc queryDescByItemId(Long itemId) {
		QueryWrapper<ItemDesc> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", itemId);
		ItemDesc itemDesc = itemDescMapper.selectOne(queryWrapper);
		return itemDesc;
	}

}
