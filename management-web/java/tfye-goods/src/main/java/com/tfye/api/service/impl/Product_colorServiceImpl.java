package com.tfye.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.Product_colorService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.manage.impl.Product_colorManagerImpl;
import com.tfye.manage.impl.Product_sizeManageImpl;
import com.tfye.utils.DateUtils;
import com.tfye.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class Product_colorServiceImpl extends BaseApiService implements Product_colorService {

	@Autowired
	private Product_color prc;
	
	@Autowired
	private Product_colorManagerImpl product_colorManageImpl;
	
	public Map<String, Object> getAllColor() {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_colorManageImpl.getAllColor());
	}
	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_colorManageImpl.Pager(page,limit));
	}
	public Map<String, Object> saveAdmin(String color) {
		// TODO 自动生成的方法存根
		System.out.println(color+"*****************************************");
		prc.setColor(color);
		prc.setCreated(DateUtils.getTimestamp());
		prc.setUpdated(DateUtils.getTimestamp());
		return product_colorManageImpl.saveAdmin(prc);
	}
	public Map<String, Object> getAllColorPro(String color) {
		// TODO 自动生成的方法存根
		List<String> list = StringUtils.getList(color);
		return setResutSuccessData(product_colorManageImpl.getAllColorPro(list));
		
	}

	
}
