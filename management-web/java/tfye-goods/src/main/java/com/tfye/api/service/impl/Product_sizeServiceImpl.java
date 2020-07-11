package com.tfye.api.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.Product_sizeServeice;
import com.tfye.common.api.BaseApiService;
import com.tfye.common.redis.BaseRedisService;
import com.tfye.entity.Product_size;

import com.tfye.manage.impl.ProductServiceManageImpl;
import com.tfye.manage.impl.Product_sizeManageImpl;
import com.tfye.utils.DateUtils;
import com.tfye.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class Product_sizeServiceImpl extends BaseApiService implements Product_sizeServeice {

	@Autowired
	private Product_sizeManageImpl product_sizeManageImpl;
	public Map<String, Object> getAllSize() {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_sizeManageImpl.getAllSize());
	}
	public Map<String, Object> saveAdmin(Product_size prz) {
		prz.setCreated(DateUtils.getTimestamp());
		prz.setUpdated(DateUtils.getTimestamp());
		return product_sizeManageImpl.saveAdmin(prz);
	}
	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_sizeManageImpl.Pager(page,limit));
	}
	public Map<String, Object> getAllSizePro(String size) {
		// TODO 自动生成的方法存根
		List<String> list = StringUtils.getList(size);
		return setResutSuccessData(product_sizeManageImpl.getAllSizePro(list));
	}
	
	                            

}
