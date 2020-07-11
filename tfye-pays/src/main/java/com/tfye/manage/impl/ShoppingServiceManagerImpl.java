package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.ShoppingDao;
import com.tfye.entity.Order;
import com.tfye.entity.Shopping;
import com.tfye.manage.ShoppingServiceManager;
import com.tfye.utils.StringUtils;

import lombok.extern.log4j.Log4j;
@Service
@Log4j
public class ShoppingServiceManagerImpl extends BaseApiService implements ShoppingServiceManager{

	@Autowired
	private ShoppingDao shoppingDao;
	public List<Shopping> getAllShopping() {
		// TODO Auto-generated method stub
		return shoppingDao.getAllShopping();
	}
	
	public void save(Shopping s) {
		// TODO Auto-generated method stub
		shoppingDao.saveOne(s);
	}


	public List<Shopping> getEnquiryShopping(int status) {
		// TODO Auto-generated method stub
		return shoppingDao.getEnquiryShopping(status);
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub\
					PageHelper.startPage( page,limit);
					List<Shopping> all = shoppingDao.getAllShopping();
					 PageInfo pageInfo=new PageInfo(all);
					 Map<String,Object> object=new HashMap<String, Object>();
				     object.put("count", pageInfo.getTotal());
				    object.put("data", pageInfo.getList());
					return object;
	}

	public Map<String, Object> updateShopping(int status, Long id) {
		// TODO 自动生成的方法存根
		try {
			shoppingDao.updateShopping(status, id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更新失败");
		}
		return setResutSuccess() ;
	}


	public Map<String, Object> updateManyShopping(int updatestatus, String id) {
		// TODO 自动生成的方法存根
		try {
			shoppingDao.updateManyShopping(updatestatus, StringUtils.getList(id));
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更新失败");
		}
		return setResutSuccess() ;
	}

	public Map<String, Object> updateAllShopping(int updatestatus, String userid) {
		// TODO 自动生成的方法存根
		try {
			shoppingDao.updateAllShopping(updatestatus, userid);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更新失败");
		}
		return setResutSuccess() ;
	}

	public Map<String, Object> getEnquiryUidShopping(int status, int userid) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(shoppingDao.getEnquiryUidShopping(status, userid));
	}

	

	public Map<String, Object> updateUidShopping(int status, Long id, int userid) {
		// TODO 自动生成的方法存根
		try {
			shoppingDao.updateUidShopping(status, id,userid);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更新失败");
		}
		return setResutSuccess() ;
	}

	public Map<String, Object> PagerUid(int page, int limit, int userid, int status) {
		// TODO 自动生成的方法存根
		PageHelper.startPage( page,limit);
		List<Shopping> all = shoppingDao.getAllUidShopping(status, userid);
		 PageInfo pageInfo=new PageInfo(all);
		 Map<String,Object> object=new HashMap<String, Object>();
	     object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return setResutSuccessData(object);
	}

	public Map<String, Object> getAllShopping(int status, int userid) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(shoppingDao.getAllUidShopping(status, userid));
	}

	public Map<String, Object> updateUidShoppingNum(int num, Long id, int userid) {
		// TODO 自动生成的方法存根
		try {
			shoppingDao.updateUidShoppingNum(num, id,userid);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更新失败");
		}
		return setResutSuccess();
		
	}
}
