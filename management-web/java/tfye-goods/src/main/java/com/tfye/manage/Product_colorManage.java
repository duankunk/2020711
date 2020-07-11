package com.tfye.manage;

import java.util.List;
import java.util.Map;

import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;


public interface Product_colorManage {

	/**
	 * 查询尺寸
	 * @return
	 */
	public List<Product_color> getAllColor();
	
	/**
	 * 增加颜色
	 * @param prc
	 * @return
	 */
	public Map<String, Object> saveAdmin(Product_color prc);
	/**
	 * 分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String , Object> Pager(int page,int limit);
	
	public java.util.List<Product_color> getAllColorPro(List<String> color);
}
