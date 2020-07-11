
package com.tfye.manage.impl;

import java.lang.annotation.Retention;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tfye.common.api.BaseApiService;
import com.tfye.constants.Constants;
import com.tfye.constants.DBTableName;
import com.tfye.constants.MQInterfaceType;
import com.tfye.dao.UserDao;
import com.tfye.entity.UserEntity;
import com.tfye.entity.address;
import com.tfye.manage.UserServiceManage;
import com.tfye.mq.roducer.RegisterMailboxProducer;
import com.tfye.utils.DateUtils;
import com.tfye.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {
	
	@Autowired
	private UserDao userDao;
	//根据id查找

	public Map<String, Object> getOne(int id) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(userDao.getOne(id));
	}

	public Map<String, Object> getOneEmail(String email) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(userDao.getUEmail(email));
	}

	public Map<String, Object> getOneName(String name) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(userDao.getUName(name));
	}

	public Map<String, Object> getOneIhone(String phone) {
		// TODO 自动生成的方法存根
		return setResutSuccessData(userDao.getUphone(phone));
	}

	public Map<String, Object> updatpassword(int id, String password) {
		// TODO 自动生成的方法存根
		String salt = userDao.getOne(id).getUaddpassword();
		password = StringUtils.getPassword(password, salt); 
		try {
			Timestamp  updated = DateUtils.getTimestamp();
			userDao.updatepassword(id, password,updated);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.toString());
			return setResutError("更改失败");
		}
		return setResutSuccess();
	}

	public Map<String, Object> updateimg(String photo, int id) {
		// TODO 自动生成的方法存根
		try {
			Timestamp  updated = DateUtils.getTimestamp();
			userDao.updateimg(photo, id);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.toString());
			return setResutError("更改失败");
		}
		return setResutSuccess();
	}

	public Map<String, Object> registeruser(String name, String phone, String email, String password)  {
		// TODO 自动生成的方法存根
		String salt = StringUtils.getSalt();
		password = StringUtils.getPassword(password, salt); 
		Timestamp  updated = DateUtils.getTimestamp();
		Timestamp  created = DateUtils.getTimestamp();
		try {
			if (userDao.getUName(name)==null) {
				if (email!=null) {
					if (userDao.getUEmail(email)==null) {
						userDao.registeruseruemail(name ,email, password, salt, created, updated);
					} else {
						return setResutError("注册失败(email已存在)");
					}
				} else if(phone!=null) {
					if (userDao.getUphone(phone)==null) {
						userDao.registeruseruphone(name, phone, password, salt, created, updated);
					} else {
						return setResutError("注册失败(phone已存在)");
					}
				} else {
					
				} 
			} else {
				return setResutError("注册失败(name已存在)");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.toString());
			return setResutError("注册失败");
		}
//		catch (MyBatisSystemException e) {
//			// TODO: handle exception
//			log.error(e.toString());
//			return setResutError("注册失败");
//		}
		return setResutSuccess();
	}
	
	@Value("${messages.queue}")
	private String MESSAGES_QUEUE;
	
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	public Map<String, Object> sendCodeEmail(String email,String company) {
		// TODO 自动生成的方法存根
		String code6 = StringUtils.getRandomCode(6);
		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
		String mailMessage = mailMessage(email, "先生/女生",code6,company,MQInterfaceType.SMS_MAIL,"mail");
		registerMailboxProducer.send(activeMQQueue, mailMessage);
		JSONObject code = new JSONObject();
		code.put("msg", "已经发送邮箱");
		code.put("code", code6);
		return setResutSuccessData(code);
	}
	private String mailMessage(String email, String userName ,String code,String company,String type,String ei) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", type);
		JSONObject content = new JSONObject();
		content.put(ei, email);
		content.put("userName", userName);
		content.put("code", code);
		content.put("company", company);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}
	public Map<String, Object> sendCodeIphone(String phone,String company) {
		// TODO 自动生成的方法存根
		String code6 = StringUtils.getRandomCode(6);
		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
		String mailMessage = mailMessage(phone, "先生/女生",code6,company,MQInterfaceType.SMS_IPHON,"iphone");
		registerMailboxProducer.send(activeMQQueue, mailMessage);
		JSONObject code = new JSONObject();
		code.put("msg", "已经发送邮箱");
		code.put("code", code6);
		return setResutSuccessData(code);
	}

	public Map<String, Object> getAlluser() {
		// TODO 自动生成的方法存根
		return setResutSuccessData(userDao.getAlluser());
	}

	public Map<String, Object> deng(String name, String password) {
		// TODO 自动生成的方法存根
			UserEntity u = userDao.deng(name, password);
			if (u!=null) {
				return setResutSuccessData(u);
			} else {
				return setResutError("登陆失败(name不存在/password错误)");
			}
	}

	public Map<String, Object> Pager(int page, int limit) {
		// TODO 自动生成的方法存根
		PageHelper.startPage( page,limit);
		List<UserEntity> all = userDao.getAlluser();
		 PageInfo pageInfo=new PageInfo(all);
		 Map<String,Object> object=new HashMap<String, Object>();
	     object.put("count", pageInfo.getTotal());
	    object.put("data", pageInfo.getList());
		return setResutSuccessData(object);
	}
}
