package com.tfye.api.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.OrderService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Order;
import com.tfye.manage.impl.OrderServiceManagerImpl;
import com.tfye.utils.DateUtils;

import lombok.extern.log4j.Log4j;
import sun.util.logging.resources.logging;

@Log4j
@RestController
public class OrderServiceImpl  extends BaseApiService implements OrderService{
	@Autowired
	private OrderServiceManagerImpl orderServiceManagerIcmpl;
	public Map<String , Object> getAllOrder() {
		// TODO Auto-generated method stub
		return setResutSuccessData(orderServiceManagerIcmpl.getAllOrder());
	}
	

	public Map<String , Object> save(Order o) {
		// TODO Auto-generated method stub
		o.setOrderstatus(0);
		o.setCreated(DateUtils.getTimestamp());
		o.setUpdated(DateUtils.getTimestamp());
		try {
			orderServiceManagerIcmpl.save(o);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e);
			return setResutError("添加订单失败");
		}
		return setResutSuccess();
	}


	public Map<String , Object> getUpdateOrder(int orderstatus,Long id) {
		// TODO Auto-generated method stub
		return setResutSuccessData(orderServiceManagerIcmpl.getUpdateOrder(orderstatus, id));
	}

//	public Map<String , Object> getUpdateAllOrder(int updatestatus, int orderstatus) {
//		// TODO Auto-generated method stub
//		return setResutSuccessData(orderServiceManagerIcmpl.getUpdateAllOrder(updatestatus, orderstatus));
//	}

	public Map<String , Object> getEnquiryOrder(int orderstatus) {
		// TODO Auto-generated method stub
		return setResutSuccessData(orderServiceManagerIcmpl.getEnquiryOrder(orderstatus));
	}


	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub
		return setResutSuccessData(orderServiceManagerIcmpl.Pager(page,limit));
	}


	public Map<String, Object> getAllOrder(int uid) {
		// TODO 自动生成的方法存根
		return orderServiceManagerIcmpl.getAllOrder(uid);
	}


	public Map<String, Object> getUpdateOrder(int orderstatus, Long id, Long uid) {
		// TODO 自动生成的方法存根
		return orderServiceManagerIcmpl.getUpdateOrder(orderstatus,id,uid);
	}


	public Map<String, Object> getEnquiryOrder(int orderstatus, int uid) {
		// TODO 自动生成的方法存根
		return orderServiceManagerIcmpl.getEnquiryOrder(orderstatus, uid);
	}


	public Map<String, Object> Pager(int page, int limit, int uid) {
		// TODO 自动生成的方法存根
		return orderServiceManagerIcmpl.Pager(page, limit, uid);
	}


	


}
