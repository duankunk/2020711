package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.Product_sizeDao;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.manage.Product_sizeManage;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Product_sizeManageImpl extends BaseApiService implements Product_sizeManage {

	@Autowired
	private Product_sizeDao pz;
	
	public List<Product_size> getAllSize() {
		// TODO Auto-generated method stub
		return pz.getAllSize();
	}
	
	public Map<String, Object> saveAdmin(Product_size prz) {
		// TODO Auto-generated method stub
		try {
			pz.savaOne(prz);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e.toString());
			return setResutError("添加失败");
		}
		
		return setResutSuccess();
	}

	public Map<String, Object> Pager(int page, int limit) {
		PageHelper.startPage( page,limit);
		List<Product_size> all = pz.getAllSize();
		PageInfo pageInfo=new PageInfo(all);
		Map<String,Object> object=new HashMap<String, Object>();
	    object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return object;
	}

	public List<Product_size> getAllSizePro(List<String> size) {
		// TODO 自动生成的方法存根
		return pz.getAllSizePro(size);
	}

}
