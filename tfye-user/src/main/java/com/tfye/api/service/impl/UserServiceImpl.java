
package com.tfye.api.service.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import com.netflix.governator.annotations.binding.Main;
import com.tfye.api.service.UserService;
import com.tfye.common.api.BaseApiService;
import com.tfye.common.redis.BaseRedisService;
import com.tfye.dao.UserDao;
import com.tfye.entity.UserEntity;
import com.tfye.entity.address;
import com.tfye.manage.UserServiceManage;
import com.tfye.manage.impl.UserServiceManageImpl;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService {

	@Autowired
	private UserServiceManageImpl userServiceManageImpl;

	public Map<String, Object> getOne(int id) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.getOne(id);
	}

	public Map<String, Object> getOneEmail(String email) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.getOneEmail(email);
	}

	public Map<String, Object> getOneName(String name) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.getOneName(name);
	}

	public Map<String, Object> getOneIhone(String phone) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.getOneIhone(phone);
	}

	public Map<String, Object> updatpassword(int id, String password) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.updatpassword(id, password);
	}

	public Map<String, Object> updateimg(String photo, int id) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.updateimg(photo, id);
	}

	public Map<String, Object> registeruser(String name, String phone, String email, String password) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.registeruser(name, phone, email, password);
	}

	public Map<String, Object> sendCodeEmail(String email,String company) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.sendCodeEmail(email, company);
	}

	public Map<String, Object> sendCodeIphone(String phone,String company) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.sendCodeIphone(phone, company);
	}

	public Map<String, Object> getAlluser() {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.getAlluser();
	}

	public Map<String, Object> deng(String name, String password) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.deng(name, password);
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO 自动生成的方法存根
		return userServiceManageImpl.Pager(page, limit);
	}
		
}
