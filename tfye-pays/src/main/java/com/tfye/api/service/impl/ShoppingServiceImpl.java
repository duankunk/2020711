package com.tfye.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.ShoppingService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Shopping;
import com.tfye.manage.impl.ShoppingServiceManagerImpl;
import com.tfye.utils.DateUtils;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class ShoppingServiceImpl extends BaseApiService implements ShoppingService{

	@Autowired
	private ShoppingServiceManagerImpl shoppingServiceManagerImpl;
	public Map<String , Object> getAllShopping() {
		// TODO Auto-generated method stub
		return setResutSuccessData(shoppingServiceManagerImpl.getAllShopping());
	}
	@Autowired
	private Shopping s;
	public Map<String, Object> save(int status, int userid, int sptiesid, int quantity, int dimensions, int scolor) {
		s.setCreated(DateUtils.getTimestamp());
		s.setUpdated(DateUtils.getTimestamp());
		s.setStatus(status);
		s.setUserid(userid);
		s.setSptiesid(sptiesid);
		s.setQuantity(quantity);
		s.setDimensions(dimensions);
		s.setScolor(scolor);
		try {
			shoppingServiceManagerImpl.save(s);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.error(e);
			return setResutError("添加订单失败");
		}
		return setResutSuccess();
	}
//	public Map<String , Object> save(Shopping s) {
//		// TODO Auto-generated method stub
//		s.setCreated(DateUtils.getTimestamp());
//		s.setUpdated(DateUtils.getTimestamp());
//		System.out.println(s.toString());
//		try {
//			shoppingServiceManagerImpl.save(s);
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			log.error(e);
//			return setResutError("添加订单失败");
//		}
//		return setResutSuccess();
//	}
	
	public Map<String , Object> updateShopping(int status, Long id) {
		// TODO Auto-generated method stub
		return setResutSuccessData(shoppingServiceManagerImpl.updateShopping(status, id));
	}
	
	public Map<String , Object> getEnquiryShopping(int status) {
		// TODO Auto-generated method stub
		return setResutSuccessData(shoppingServiceManagerImpl.getEnquiryShopping(status));
	}
	
	public Map<String, Object> Pager(int page, int limit) {
		// TODO Auto-generated method stub
		return setResutSuccessData(shoppingServiceManagerImpl.Pager(page,limit));
	}

	public Map<String, Object> updateManyShopping(int updatestatus, String id) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.updateManyShopping( updatestatus,  id);
	}

	public Map<String, Object> updateAllShopping(int updatestatus, String userid) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.updateAllShopping(updatestatus,  userid);
	}

	public Map<String, Object> getEnquiryUidShopping(int status, int userid) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.getEnquiryUidShopping(status,userid);
	}

	

	public Map<String, Object> updateUidShopping(int status, Long id, int userid) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.updateUidShopping(status, id, userid);
	}

	public Map<String, Object> PagerUid(int page, int limit, int userid,int status) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.PagerUid(page, limit, userid, status);
	}

	public Map<String, Object> getAllShopping(int status, int userid) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.getAllShopping(status, userid);
	}

	public Map<String, Object> updateUidShoppingNum(int num, Long id, int userid) {
		// TODO 自动生成的方法存根
		return shoppingServiceManagerImpl.updateUidShoppingNum(num,id,userid);
	}

	

	

}
