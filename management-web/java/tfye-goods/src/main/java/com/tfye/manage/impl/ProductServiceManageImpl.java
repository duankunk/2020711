package com.tfye.manage.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.InventoryDao;
import com.tfye.dao.Product_colorDao;
import com.tfye.dao.Product_sizeDao;
import com.tfye.dao.productDao;
import com.tfye.entity.Product;

import com.tfye.manage.ProductServiceManage;
import com.tfye.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ProductServiceManageImpl extends BaseApiService implements ProductServiceManage {
	
	@Autowired
	private productDao pd;
	@Autowired
	private Product_colorDao pc;
	@Autowired
	private Product_sizeDao pz;
	@Autowired
	private InventoryDao inventoryDao;
	public Product findById(int id) {
		// TODO Auto-generated method stubs
		return pd.findById(id);
	}

	public Map<String, Object> saveAdmin(Product pro) {
		try {
			pd.savaOne(pro);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e.toString());
			return setResutError("添加失败");
		}
		
		return setResutSuccess();
	}

	public List<Product> getProductAll() {
		
		return pd.getProductAll();
	}

	public Map<String, Object> Pager(int page, int limit) {
		PageHelper.startPage( page,limit);
		List<Product> all = pd.getProductAll();
		PageInfo pageInfo=new PageInfo(all);
		Map<String,Object> object=new HashMap<String, Object>();
	    object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return object;
	}

//	public List<Product> Firstsales() {
//		// TODO Auto-generated method stub
//		return pd.Firstsales();
//	}

	public List<Product> FirstCreated() {
		// TODO Auto-generated method stub
		return pd.FirstCreated();
	}

	public Map<String , Object> UpdataProduct(Product pro) {
		// TODO Auto-generated method stub
		try {
			pd.UpdataProduct(pro);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e.toString());
			return setResutError("更新失败");
		}
		return setResutSuccess();
	}

	
	public Map<String, Object> findByManyid(String id) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(pd.getAllProductString(StringUtils.getList(id)));
	}

	public Map<String, Object> findByidAllInfo(int id) {
		// TODO 自动生成的方法存根
		Product p = pd.findById(id);
		Map<String, Object> o = requ(p);
		return setResutSuccessData(o);
	}
	
	
	public Map<String, Object> requ(Product p) {
		Map<String,Object> object=new HashMap<String, Object>();
		object.put("pro", p);
		object.put("color", pc.getAllColorPro(StringUtils.getList(p.getColor())));
		object.put("size", pz.getAllSizePro(StringUtils.getList(p.getSize())));
		object.put("inventoryDao", inventoryDao.getOneInventory(Long.toString(p.getId())));
		return object;
		
	}

}
