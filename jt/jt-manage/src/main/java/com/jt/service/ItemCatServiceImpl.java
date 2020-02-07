package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public String queryNameByItemCatId(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName();
	}

	@Override
	public List<EasyUITree> queryTreeList(Long parentId) {
		List<EasyUITree> treeList = new ArrayList<>();
		QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		List<ItemCat> selectList = itemCatMapper.selectList(queryWrapper);
		for (int i = 0; i < selectList.size(); i++) {
			treeList.add(new EasyUITree(selectList.get(i).getId(), selectList.get(i).getName(), selectList.get(i).getIsParent()?"closed":"open"));
		}
		return treeList;
	}

}
