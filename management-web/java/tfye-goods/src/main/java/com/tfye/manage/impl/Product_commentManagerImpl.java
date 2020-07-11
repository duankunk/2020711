package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.Product_commentDao;
import com.tfye.dao.Product_sizeDao;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_comment;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class Product_commentManagerImpl extends BaseApiService implements com.tfye.manage.Product_commentManager {
	
	@Autowired
	private Product_commentDao pm;
	public Map<String, Object> saveAdmin(Product_comment prm) {
		// TODO Auto-generated method stub
		try {
			pm.savaOne(prm);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e.toString());
			return setResutError("添加失败");
		}
		return setResutSuccess();
	}
	public List<Product_comment> getAllComment() {
		// TODO Auto-generated method stub
		return pm.getAllComment();
	}
	public Map<String, Object> Pager(int page, int limit) {
		PageHelper.startPage( page,limit);
		List<Product_comment> all = pm.getAllComment();
		PageInfo pageInfo=new PageInfo(all);
		Map<String,Object> object=new HashMap<String, Object>();
	    object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return object;
	}

}
