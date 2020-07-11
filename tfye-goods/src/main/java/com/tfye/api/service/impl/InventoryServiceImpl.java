package com.tfye.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.InventoryService;
import com.tfye.api.service.ProductService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Inventory;
import com.tfye.entity.Product;
import com.tfye.manage.impl.InventoryManagerImpl;
import com.tfye.manage.impl.ProductServiceManageImpl;
import com.tfye.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InventoryServiceImpl extends BaseApiService implements InventoryService{
	
	@Autowired
	private InventoryManagerImpl inventoryManagerImpl;
	
	public Map<String, Object> getAllInventory() {
		// TODO 自动生成的方法存根
		return inventoryManagerImpl.getAllInventory();
	}

	public Map<String, Object> saveOne(Inventory inventory) {
		// TODO 自动生成的方法存根
		inventory.setCreated(DateUtils.getTimestamp());
		inventory.setUpdated(DateUtils.getTimestamp());
		return inventoryManagerImpl.saveOne(inventory);
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO 自动生成的方法存根
		return inventoryManagerImpl.Pager(page, limit);
	}

	
	public Map<String, Object> getOneInventory(String pid) {
		// TODO 自动生成的方法存根
		return null;
	}
	
	
}
