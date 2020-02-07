package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public EasyUITable queryItems(Integer page, Integer rows) {
		IPage<Item> ipage = new Page<Item>();
		ipage.setCurrent(page)
				.setSize(rows);
		QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>();
		queryWrapper.orderByDesc("updated");
		IPage<Item> selectPage = itemMapper.selectPage(ipage, queryWrapper);
		List<Item> list = selectPage.getRecords();
		long total = selectPage.getTotal();
		return new EasyUITable(total, list);
	}

	@Transactional
	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1)
				.setCreated(new Date())
				.setUpdated(item.getCreated());
		itemMapper.insert(item);
		itemDesc.setItemId(item.getId())
					.setCreated(item.getCreated())
					.setUpdated(item.getCreated());
		itemDescMapper.insert(itemDesc);
	}

	@Override
	public void deleteItemById(Long[] ids) {
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
	}

	@Override
	public void changeStatus(int status, Long[] ids) {
		Item item = new Item();
		item.setStatus(status)
				.setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", Arrays.asList(ids));
		itemMapper.update(item, updateWrapper);
	}
	@Transactional
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId())
				.setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
		
	}

}
