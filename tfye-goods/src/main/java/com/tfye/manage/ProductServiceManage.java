package com.tfye.manage;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Product;
import com.tfye.entity.Product_comment;



public interface ProductServiceManage {
	
	public Map<String , Object> findByidAllInfo(int id);
	public Map<String , Object> findByManyid(String id);
	/**
	 * 查询单个商品
	 */
	public Product findById(int id);
	/**
	 * 增加商品
	 */
	public Map<String, Object> saveAdmin(String photo,String name,String price,
			double discount,  String describe, String over,
			 String color,
			 String size, Timestamp overtime,Timestamp created,Timestamp updated);
	/**
	 * 显示所有商品
	 */
	public List<Product> getProductAll();
	/**
	 * 分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String , Object> Pager(int page,int limit);
//	/**
//	 * 查询销量最高的
//	 * @param pro
//	 * @return
//	 */
//	public List<Product> Firstsales();
	/**
	 * 查询最新商品的
	 * @param pro
	 * @return
	 */
	public List<Product> FirstCreated();
	/**
	 * 修改商品
	 * @param pro
	 */
	public Map<String , Object> UpdataProduct(Product pro);
	
}
