package com.tfye.manage.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.dao.AddressDao;
import com.tfye.entity.address;
import com.tfye.manage.AddressServiseManage;
import com.tfye.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AddressServiceManageImpl extends BaseApiService implements AddressServiseManage {
  @Autowired
  private AddressDao addressDao;

public Map<String, Object> deleteaddress(int id, int uid) {
	try {
		// TODO 自动生成的方法存根
		addressDao.deleteaddress(id, uid);
	} catch (Exception e) {
		// TODO: handle exception
		log.error(e.toString());
		return setResutError("删除失败");
	}
	return setResutSuccess();
}

public Map<String, Object> updateaddress(int id, String province, String town, String area, String address,
		String cellphone, String name, int uid) {
	// TODO 自动生成的方法存根
	try {
		// TODO 自动生成的方法存根
		Timestamp  updated = DateUtils.getTimestamp();
		addressDao.updateaddress(id, province, town, area, address, cellphone, name, uid,updated);
	} catch (Exception e) {
		// TODO: handle exception
		log.error(e.toString());
		return setResutError("更改失败");
	}
	return setResutSuccess();
}

public Map<String, Object> addaddress(String province, String town, String area, String cellphone, String address,
		String name, int uid) {
	// TODO 自动生成的方法存根aid=uid
	try {
		// TODO 自动生成的方法存根
		Timestamp  updated = DateUtils.getTimestamp();
		Timestamp  created = DateUtils.getTimestamp();
		if (addressDao.selectnum(""+uid)==null) {
			addressDao.addaddress1(province,town,area,cellphone,address,name,uid,updated,created);
		} else {
			addressDao.addaddress(province,town,area,cellphone,address,name,uid,updated,created);
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		log.error(e.toString());
		return setResutError("添加失败");
	}
	
	return setResutSuccess();
}

public Map<String, Object> selectnum(String uid) {
	// TODO 自动生成的方法存根
	return  setResutSuccessData(addressDao.selectnum(uid));
}


public Map<String, Object> getAlladdress() {
	// TODO 自动生成的方法存根
	return setResutSuccessData(addressDao.getAlladdress());
}

public Map<String, Object> PagerUid(int page, int limit,int uid) {
	PageHelper.startPage( page,limit);
	List<address> all = addressDao.selectnum(""+uid);
	 PageInfo pageInfo=new PageInfo(all);
	 Map<String,Object> object=new HashMap<String, Object>();
     object.put("count", pageInfo.getTotal());
    object.put("data", pageInfo.getList());
	return setResutSuccessData(object);
}

public Map<String, Object> updateaddress(int id, int uid, int ido) {
	// TODO 自动生成的方法存根
	try {
		// TODO 自动生成的方法存根
		Timestamp  updated = DateUtils.getTimestamp();
		Timestamp  created = DateUtils.getTimestamp();
		addressDao.updateaddressStatus( ido,  uid);
		addressDao.updateaddressStatus1( uid,  id);
	} catch (Exception e) {
		// TODO: handle exception
		log.error(e.toString());
		return setResutError("更改失败");
	}
	
	return setResutSuccess();
}

}
