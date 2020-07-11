package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.InventoryDao;
import com.tfye.dao.Product_colorDao;
import com.tfye.dao.Product_sizeDao;
import com.tfye.entity.Inventory;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.manage.InventoryManage;
import com.tfye.manage.Product_colorManage;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class InventoryManagerImpl extends BaseApiService implements InventoryManage {
	
	@Autowired
	private InventoryDao inventoryDao;
	
	public Map<String, Object> getAllInventory() {
		// TODO 自动生成的方法存根
		return setResutSuccessData(inventoryDao.getAllInventory());
	}

	public Map<String, Object> saveOne(Inventory inventory) {
		// TODO 自动生成的方法存根
		try {
			inventoryDao.saveOne(inventory);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.info(e.toString());
			return setResutError("添加失败");
		}
		return setResutSuccess();
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO 自动生成的方法存根
		PageHelper.startPage( page,limit);
		List<Inventory> all = inventoryDao.getAllInventory();
		PageInfo pageInfo=new PageInfo(all);
		Map<String,Object> object=new HashMap<String, Object>();
	    object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return setResutSuccessData(object);
	}

	public Map<String, Object> getOneInventory(String pid) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(inventoryDao.getOneInventory(pid));
	}



	

}
