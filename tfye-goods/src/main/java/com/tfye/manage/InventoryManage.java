package com.tfye.manage;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Inventory;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;


public interface InventoryManage {

	public Map<String, Object> getOneInventory(String pid);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:33:57
	 * getAllInventory作用 ： (得到所有库存)
	 * 返回类型 ：Map<String,Object>
	 */
	public Map<String, Object> getAllInventory();
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:33:36
	 * saveOne作用 ： (保存一个库存)
	 * 返回类型 ：Map<String,Object>
	 */
	public Map<String, Object> saveOne(Inventory inventory);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:34:11
	 * Pager作用 ： (得到库存分页)
	 * 返回类型 ：Map<String,Object>
	 */
	public Map<String , Object> Pager(int page,int limit);
}
