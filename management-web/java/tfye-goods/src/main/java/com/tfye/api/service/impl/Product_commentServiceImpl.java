package com.tfye.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.Product_commentService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Product_comment;
import com.tfye.entity.Product_size;
import com.tfye.manage.impl.Product_colorManagerImpl;
import com.tfye.manage.impl.Product_commentManagerImpl;
import com.tfye.manage.impl.Product_sizeManageImpl;
import com.tfye.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class Product_commentServiceImpl extends BaseApiService implements Product_commentService {
	
	@Autowired
	private Product_commentManagerImpl product_commentManagerImpl;
	public Map<String, Object> saveAdmin(Product_comment prm) {
		prm.setCreated(DateUtils.getTimestamp());
		prm.setUpdated(DateUtils.getTimestamp());
		
		return product_commentManagerImpl.saveAdmin(prm);
	}
	
	public Map<String, Object> getAllComment() {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_commentManagerImpl.getAllComment());
	}
	
	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub
		return setResutSuccessData(product_commentManagerImpl.Pager(page,limit));
	}

}
