package com.tfye.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.api.service.AddressService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.UserEntity;
import com.tfye.entity.address;
import com.tfye.manage.impl.AddressServiceManageImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AddressServiceImpl extends BaseApiService implements AddressService {
	@Autowired
	private AddressServiceManageImpl addressServiceManageImpl;

	public Map<String, Object> deleteaddress(int id, int uid) {
		 
		return addressServiceManageImpl.deleteaddress(id, uid);
	}
	public Map<String, Object> updateaddress(int id, String province, String town, String area, String address,
			String cellphone, String name, int uid) {
		return addressServiceManageImpl.updateaddress(id, province, town, area, address, cellphone, name, uid);
	}

	public Map<String, Object> addaddress(String province, String town, String area, String cellphone, String address,
			String name, int uid) {
	    
		return addressServiceManageImpl.addaddress(province, town, area, cellphone, address, name, uid);
	}
	public Map<String, Object> selectnum(String uid) {
		return addressServiceManageImpl.selectnum(uid);
		
	}
	public Map<String, Object> getAlladdress() {
		return addressServiceManageImpl.getAlladdress();
	}
	public Map<String, Object> PagerUid(int page, int limit,int uid) {
		return addressServiceManageImpl.PagerUid(page, limit, uid);
	}
	public Map<String, Object> updateaddress(int id, int uid, int ido) {
		return addressServiceManageImpl.updateaddress(id, uid, ido);
	}
}
