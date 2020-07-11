package com.tfye.manage;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Order;
import com.tfye.entity.Shopping;

public interface ShoppingServiceManager {
	
	public List<Shopping> getAllShopping();
	
	public void save(Shopping s);

	public Map<String, Object> updateShopping(int status,Long id);
//	
//	public List<Shopping> getUpdateAllShopping(int updatestatus,int status);

	public List<Shopping> getEnquiryShopping(int status);
	
	public Map<String , Object> Pager(int page,int limit);
	
	
	public Map<String, Object> updateManyShopping(int updatestatus, String id);

	public Map<String, Object> updateAllShopping(int updatestatus, String userid);
	
	public Map<String, Object> getEnquiryUidShopping(int status, int userid);


	public Map<String, Object> updateUidShopping(int status, Long id, int userid);

	public Map<String, Object> PagerUid(int page, int limit, int userid,int status );

	public Map<String, Object> getAllShopping(int status, int userid);
}
