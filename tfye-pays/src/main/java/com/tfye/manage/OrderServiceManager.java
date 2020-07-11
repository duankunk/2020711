package com.tfye.manage;

import java.util.List;
import java.util.Map;

import com.tfye.api.service.OrderService;
import com.tfye.entity.Order;

public interface OrderServiceManager {
	
	
	public List<Order> getAllOrder();
	
	
	public void save(Order o);
	
	public Map<String , Object> getUpdateOrder(int orderstatus,Long id);
	
//	public List<Order> getUpdateAllOrder(int updatestatus,int orderstatus);

	public List<Order> getEnquiryOrder(int orderstatus);
	
	public Map<String , Object> Pager(int page,int limit);
	
	
	public Map<String, Object> getAllOrder(int uid);


	public Map<String, Object> getUpdateOrder(int orderstatus, Long id, Long uid);



	public Map<String, Object> getEnquiryOrder(int orderstatus, int uid);


	public Map<String, Object> Pager(int page, int limit, int uid);
}
