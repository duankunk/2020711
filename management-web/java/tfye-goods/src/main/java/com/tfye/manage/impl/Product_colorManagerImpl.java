package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.Product_colorDao;
import com.tfye.dao.Product_sizeDao;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.manage.Product_colorManage;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Product_colorManagerImpl extends BaseApiService implements Product_colorManage {


	@Autowired
	private Product_colorDao pc;
	
	public Map<String, Object> saveAdmin(Product_color prc) {
		// TODO Auto-generated method stub
		try {
			pc.savaOne(prc);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.info(e.toString());
			return setResutError("添加失败");
		}
		return setResutSuccess();
	}

	public List<Product_color> getAllColor() {
		// TODO Auto-generated method stub
		return pc.getAllColor();
	}

	public Map<String, Object> Pager(int page, int limit) {
		PageHelper.startPage( page,limit);
		List<Product_color> all = pc.getAllColor();
		PageInfo pageInfo=new PageInfo(all);
		Map<String,Object> object=new HashMap<String, Object>();
	    object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return object;
	}

	public List<Product_color> getAllColorPro(List<String> color) {
		// TODO 自动生成的方法存根
		return pc.getAllColorPro(color);
	}
	

}
