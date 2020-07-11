package com.tfye.manage;

import java.util.List;
import java.util.Map;

import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;


public interface Product_sizeManage {
	/**
	 * 查询尺寸
	 * @return
	 */
	public List<Product_size> getAllSize();
	
	/**
	 * 增加尺寸
	 * @param prz
	 * @return
	 */
	public Map<String, Object> saveAdmin(Product_size prz);
	/**
	 * 分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String , Object> Pager(int page,int limit);
	
	
	public List<Product_size>  getAllSizePro(List<String> color);
}
