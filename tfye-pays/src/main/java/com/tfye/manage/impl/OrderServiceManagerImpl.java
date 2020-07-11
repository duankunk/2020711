package com.tfye.manage.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.OrderDao;
import com.tfye.entity.Order;
import com.tfye.manage.OrderServiceManager;

import lombok.extern.log4j.Log4j;


@Service
@Log4j
public class OrderServiceManagerImpl extends BaseApiService implements OrderServiceManager{

	@Autowired
	private OrderDao orderDao;

	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return orderDao.getAllOrder();
	}


	public void save(Order o) {
		// TODO Auto-generated method stub
		orderDao.saveOne(o);
	}

	public Map<String, Object> getUpdateOrder(int orderstatus,Long id) {
		try {
			// TODO Auto-generated method stub
			orderDao.getUpdateOrder(orderstatus, id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更改失败");
		}
		return setResutSuccess();
	}

//	public List<Order> getUpdateAllOrder(int updatestatus, int orderstatus) {
//		// TODO Auto-generated method stub
//		return orderDao.getUpdateAllOrder(updatestatus,orderstatus);
//	}
//	

	public List<Order> getEnquiryOrder(int orderstatus) {
		// TODO Auto-generated method stub 
		return orderDao.getEnquiryOrder(orderstatus);
	}

	
	/**
	 * 分页
	 */
		public Map<String, Object> Pager(int page, int limit) {
			// TODO Auto-generated method stub\
			PageHelper.startPage( page,limit);
			List<Order> all = orderDao.getAllOrder();
			 PageInfo pageInfo=new PageInfo(all);
			 Map<String,Object> object=new HashMap<String, Object>();
		     object.put("count", pageInfo.getTotal());
		    object.put("data", pageInfo.getList());
			return object;
		}


	public Map<String, Object> getAllOrder(int uid) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(orderDao.getAllUidOrder(uid));
	}

	public Map<String, Object> getUpdateOrder(int orderstatus, Long id, Long uid) {
		// TODO 自动生成的方法存根
		
		try {
			// TODO Auto-generated method stub
			System.out.println(id+"fffffffffffffffffffffffffffffff");
			orderDao.getUpdateUidOrder(orderstatus,id,uid);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			return setResutError("更改失败");
		}
		return setResutSuccess();
	}


	public Map<String, Object> getEnquiryOrder(int orderstatus, int uid) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(orderDao.getEnquiryUidOrder(orderstatus,uid));
	}


	public Map<String, Object> Pager(int page, int limit, int uid) {
		// TODO 自动生成的方法存根
		PageHelper.startPage( page,limit);
		List<Order> all = orderDao.getAllUidOrder(uid);
		 PageInfo pageInfo=new PageInfo(all);
		 Map<String,Object> object=new HashMap<String, Object>();
	     object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return setResutSuccessData(object);
	}


	
        
    
}
