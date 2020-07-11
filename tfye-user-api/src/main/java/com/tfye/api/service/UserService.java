
package com.tfye.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.UserEntity;
import com.tfye.entity.address;


@RequestMapping("/user")
public interface UserService {
		//������д�����·��
	/**
	 * 
	 * @param id
	 * @return
	 */
	//����id�����û�
	@RequestMapping("/selectone")
	public Map<String , Object> getOne(@RequestParam("id") int id);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午11:14:56
	 * getOneEmail作用 ： (通过email查找用户)
	 * 返回类型 ：UserEntity
	 */
	@RequestMapping("/getOneEmail")
	public Map<String , Object> getOneEmail(@RequestParam("email") String email);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午11:15:16
	 * getOneName作用 ： (通过name查找用户)
	 * 返回类型 ：UserEntity
	 */
	@RequestMapping("/getOneName")
	public Map<String , Object> getOneName(@RequestParam("name") String name);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午11:15:29
	 * getOneIhone作用 ： (通过phone查找用户)
	 * 返回类型 ：UserEntity
	 */
	@RequestMapping("/getOneIhone")
	public Map<String , Object> getOneIhone(@RequestParam("phone") String phone);
	//�޸�����
	@RequestMapping("/updatepassword")
	public Map<String , Object> updatpassword(@RequestParam("id") int id,@RequestParam("password") String password);
	//�ϴ�ͼƬ
	@RequestMapping("/updateimg")
	public Map<String , Object> updateimg(@RequestParam("photo")  String photo,@RequestParam("id") int id);
	//ע���û�
	@RequestMapping("/registeruser")
	public Map<String , Object> registeruser(@RequestParam("name") String name,@RequestParam("phone") String phone,
	@RequestParam("email") String email,@RequestParam("password") String password);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午10:49:18
	 * sendCodeEmail作用 ： (发送验证邮箱编码)
	 * 返回类型 ：List<UserEntity>
	 */
	@RequestMapping("/sendCodeEmail")
	public Map<String , Object> sendCodeEmail(@RequestParam("email") String email,@RequestParam("company") String company);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午10:49:18
	 * sendCodeEmail作用 ： (发送验证电话编码)
	 * 返回类型 ：List<UserEntity>
	 */
	@RequestMapping("/sendCodeIphone")
	public Map<String , Object> sendCodeIphone(@RequestParam("phone") String phone,@RequestParam("company") String company);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:10:21
	 * Pager作用 ： (更具用户地址分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/Pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
	//��ѯ��������
	@RequestMapping("/getAlluser")
	public Map<String , Object> getAlluser();
	//��¼
	@RequestMapping("/deng")
	public Map<String , Object> deng(@RequestParam("name") String name,@RequestParam("password") String password);
}
