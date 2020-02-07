package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUITree;

public interface ItemCatService {

	String queryNameByItemCatId(Long itemCatId);

	List<EasyUITree> queryTreeList(Long parentId);

}
