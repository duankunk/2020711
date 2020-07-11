package com.tfye.api.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.ProductService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Product;
import com.tfye.manage.impl.ProductServiceManageImpl;
import com.tfye.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductServiceImpl extends BaseApiService implements ProductService{
	
	
	@Autowired
	private ProductServiceManageImpl productServiceManageImpl;
	
	
	public Map<String, Object> findByid(int id){
		
		return setResutSuccessData(productServiceManageImpl.findById(id));
	}

	public Map<String, Object> getProductAll() {
		
		return setResutSuccessData(productServiceManageImpl.getProductAll());
	}

	public Map<String, Object> saveAdmin(String photo,String name,String price,
			double discount,  String describe, String over,
			 String color,
			 String size) {
		return productServiceManageImpl.saveAdmin( photo, name, price,
				 discount,   describe,  over,
				  color,
				  size,  DateUtils.getTimestamp(), DateUtils.getTimestamp(), DateUtils.getTimestamp());
	}

//	public Map<String, Object> Firstsales() {
//		// TODO Auto-generated method stub
//		return setResutSuccessData(productServiceManageImpl.Firstsales());
//	}

	public Map<String, Object> FirstCreated() {
		// TODO Auto-generated method stub
		return setResutSuccessData(productServiceManageImpl.FirstCreated());
	}

	public Map<String, Object> UpdataProductOver(Product pro) {
		pro.setUpdated(DateUtils.getTimestamp());
		
		return productServiceManageImpl.UpdataProduct(pro);
	}


	public Map<String, Object> findByidAllInfo(int id) {
		// TODO 自动生成的方法存根
		return productServiceManageImpl.findByidAllInfo(id);
	}

	public Map<String, Object> findByManyid(String id) {
		// TODO 自动生成的方法存根
		return productServiceManageImpl.findByManyid(id);
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(productServiceManageImpl.Pager(page, limit));
	}


	
	
}
