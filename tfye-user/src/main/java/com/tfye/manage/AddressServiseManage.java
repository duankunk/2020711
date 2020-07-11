package com.tfye.manage;

import java.util.Map;

public interface AddressServiseManage {

	public Map<String, Object> deleteaddress(int id, int uid);
	public Map<String, Object> updateaddress(int id, String province, String town, String area, String address,
			String cellphone, String name, int uid);
	public Map<String, Object> addaddress(String province, String town, String area, String cellphone, String address,
			String name, int aid);
	public Map<String, Object> selectnum(String uid);
	public Map<String, Object> getAlladdress();
	public Map<String, Object> PagerUid(int page, int limit,int uid);
	public Map<String, Object> updateaddress(int id, int uid, int ido);
}
