package com.tfye.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.entity.UserEntity;
import com.tfye.feign.OrderFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.feign.UserFeign;
import com.tfye.utils.MD5Util;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
@RequestMapping("/login") 
public class Login {
	
	
	
	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	@RequestMapping("") 
	public String index() {
		
		return "login"; // /ftl/index.html
	}
	
	@RequestMapping("/findpwd") 
	public String findpwd() {
		
		return "updatepassword"; // /ftl/index.html
	}
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午2:22:32
	 * loginUser作用 ： (通過用户与密码登陆)
	 * 返回类型 ：String
	 */
    @ResponseBody
    @RequestMapping(value = "/loginUser")
    public String loginUser(HttpServletResponse response,@RequestParam(value = "name") String name,@RequestParam(value = "password") String password) {
    	Map<String, Object> data = userFeign.deng(name, password);
    	Map<String, Object> result = (Map<String, Object>)ResultUtils.getResultMap(data);
    	if (result==null) return "登陆失败";
    	String token = MD5Util.MD5(name);
    	baseRedisService.set(token, ""+result.get("id"), Long.parseLong("14400"));  // 4 * 60 *60 = 14400s 4个小时
    	baseRedisService.set(""+result.get("id"),  result.get("uname"), Long.parseLong("14400"));
    	CookieUtil.addCookie(response, "token", token, 14400);
    	return "200";
    }
    /**
     * 
     * 作者 ： kun
     * 编辑日期 ： 下午2:22:59
     * loginEmailUser作用 ： (通过邮箱登陆用户)
     * 返回类型 ：String
     */
    @ResponseBody
    @RequestMapping(value = "/loginEmailUser")
    public String loginEmailUser(HttpServletResponse response,@RequestParam(value = "email") String email, @RequestParam(value = "code") String code) {
    	String ei = baseRedisService.get(code);
    	if (!ei.equals(email)) return "邮箱错误";
		Map<String, Object> data = userFeign.getOneEmail(email);
		Map<String, Object> result = (Map<String, Object>)ResultUtils.getResultMap(data);
    	if (result==null) return "登陆失败";
    	String token = MD5Util.MD5((String) result.get("uname"));
    	System.out.println(result);
    	baseRedisService.setString(token, ""+result.get("id"), Long.parseLong("14400"));  // 4 * 60 *60 = 14400s 4个小时
    	baseRedisService.setString(""+result.get("id"), ""+result.get("uname"), Long.parseLong("14400"));
    	CookieUtil.addCookie(response, "token", token, 14400);
    	return "200";
    }
    /**
     * 
     * 作者 ： kun
     * 编辑日期 ： 下午2:23:12
     * loginIphoneUser作用 ： (电话登陆用户)
     * 返回类型 ：String
     */
    @ResponseBody
    @RequestMapping(value = "/loginIphoneUser")
    public String loginIphoneUser(HttpServletResponse response,@RequestParam(value = "iphone") String iphone,@RequestParam(value = "code") String code) {
    	String ei = baseRedisService.get(code);
    	if (!ei.equals(iphone)) return "电话错误";
		Map<String, Object> data = userFeign.getOneIhone(iphone);
		Map<String, Object> result = (Map<String, Object>)ResultUtils.getResultMap(data);
    	if (result==null) return "登陆失败";
    	String token = MD5Util.MD5((String) result.get("uname"));
    	baseRedisService.setString(token, ""+result.get("id"), Long.parseLong("14400"));  // 4 * 60 *60 = 14400s 4个小时
    	baseRedisService.setString(""+result.get("id"), ""+result.get("uname"), Long.parseLong("14400"));
    	CookieUtil.addCookie(response, "token", token, 14400);
    	return "200";
    }
	
	
}
