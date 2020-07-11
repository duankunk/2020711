package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Inventory;
import com.tfye.entity.Product;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;

@RequestMapping("/inventory")
public interface InventoryService {
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午3:02:45
	 * getAllInventory作用 ： (得到一个商品的库存)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getOneInventory")
	public Map<String, Object> getOneInventory(@RequestParam("pid") String pid);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:33:57
	 * getAllInventory作用 ： (得到所有库存)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getAllInventory")
	public Map<String, Object> getAllInventory();
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:33:36
	 * saveOne作用 ： (保存一个库存)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/add")
	public Map<String, Object> saveOne(@RequestParam("inventory") Inventory inventory);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:34:11
	 * Pager作用 ： (得到库存分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
	
	

}
