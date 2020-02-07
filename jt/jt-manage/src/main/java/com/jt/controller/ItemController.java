package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemCatService;
import com.jt.service.ItemDescService;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
/**
 * 商品控制层
 * @author DM
 *
 */
import com.jt.vo.EasyUITree;
import com.jt.vo.SysResult;
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemCatService itemCatService;
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/query")
	public EasyUITable queryItems(Integer page,Integer rows){
		return itemService.queryItems(page ,rows);
	}
	
	@RequestMapping("/cat/queryItemName")
	public String queryItemName(Long itemCatId) {
		return itemCatService.queryNameByItemCatId(itemCatId);
	}
	
	@RequestMapping("/cat/list")
	public List<EasyUITree> queryTree(@RequestParam(name = "id",defaultValue = "0") Long parentId) {
		return itemCatService.queryTreeList(parentId);
	}
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		itemService.saveItem(item,itemDesc);
		return SysResult.success();
	}
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids) {
		itemService.deleteItemById(ids);
		return SysResult.success();
	}
	@RequestMapping("/instock")
	public SysResult itemInstock(Long[] ids) {
		int status = 2;
		itemService.changeStatus(status , ids);
		return SysResult.success();
	}
	@RequestMapping("/reshelf")
	public SysResult itemReshelf(Long[] ids) {
		int status = 1;
		itemService.changeStatus(status , ids);
		return SysResult.success();
	}
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult queryDesc(@PathVariable Long itemId) {
		ItemDesc itemDesc = itemDescService.queryDescByItemId(itemId);
		return SysResult.success(itemDesc);
	}
	@RequestMapping("/update")
	public SysResult updateItem(Item item,ItemDesc itemDesc) {
		itemService.updateItem(item,itemDesc);
		return SysResult.success();
	}
}
