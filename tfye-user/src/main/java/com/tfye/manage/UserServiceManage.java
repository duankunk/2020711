
package com.tfye.manage;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.UserEntity;

public interface UserServiceManage {
	/**
	 * 
	 * @methodDesc: 功能描述:(注册服务)
	 * @param: @param
	 *             UserEntity
	 * @return 
	 */
    //����id����
	public Map<String, Object> getOne(int id);
	public Map<String, Object> getOneEmail(String email) ;
	public Map<String, Object> getOneName(String name) ;

	public Map<String, Object> getOneIhone(String phone);

	public Map<String, Object> updatpassword(int id, String password);

	public Map<String, Object> updateimg(String photo, int id) ;

	public Map<String, Object> registeruser(String name, String phone, String email, String password);
	public Map<String, Object> sendCodeEmail(String email,String company);

	public Map<String, Object> sendCodeIphone(String phone,String company) ;
	public Map<String, Object> getAlluser();

	public Map<String, Object> deng(String phone, String password);
}
