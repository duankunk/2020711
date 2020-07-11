package com.tfye.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.feign.OrderFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.feign.UserFeign;
import com.tfye.utils.ResultUtils;

@Controller
@RequestMapping("/register") 

public class Register {
	
	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	@RequestMapping("/") 
	public String index() {
		
		return PageConstants.REGISTER; // /ftl/index.html
	}
	
/**
 * 
 * 作者 ： kun
 * 编辑日期 ： 上午11:38:29
 * sendEmailCode作用 ： (发送邮箱)
 * 返回类型 ：String
 */
  @ResponseBody
  @RequestMapping(value = "/sendEmailCode")
  public String sendEmailCode(@RequestParam(value = "email") String email) {
      Map<String, Object> codeData = userFeign.sendCodeEmail(email, "tfye");
      Map<String, Object> result = (Map<String, Object>)ResultUtils.getResultMap(codeData);
  	  if (result==null) return "发送失败";
      String code = (String)result.get("code");
      String msg = (String)result.get("msg");
      baseRedisService.set(code, email, Long.parseLong("300"));
	return msg;
     
  }
  /**
   *    
   * 作者 ： kun
   * 编辑日期 ： 上午11:38:43
   * sendIphoneCode作用 ： (发送注册电话验证码)
   * 返回类型 ：String
   */
  @ResponseBody
  @RequestMapping(value = "/sendIphoneCode")
  public String sendIphoneCode(@RequestParam(value = "iphone") String iphone) {
	  Map<String, Object> codeData = userFeign.sendCodeIphone(iphone, "tfye");
	  Map<String, Object> result = (Map<String, Object>)ResultUtils.getResultMap(codeData);
  	  if (result==null) return "发送失败";
      String code = (String)result.get("code");
      String msg = (String)result.get("msg");
      baseRedisService.set(code, iphone, Long.parseLong("300"));
	return msg;
  }
  /**
   * 
   * 作者 ： kun
   * 编辑日期 ： 上午11:38:59
   * registerUser作用 ： (注册用户)
   * 返回类型 ：String
   */
  @ResponseBody
  @RequestMapping(value = "/registerUser")
  public String registerUser( @RequestParam(value = "note") String note, @RequestParam(value = "password") String password, @RequestParam(value = "name") String name,@RequestParam(value = "code") String code) {
	  String ie = baseRedisService.get(code);
	  if (ie!=null) {
		  if (ie.contains("@")) {
			  Map<String, Object> data = userFeign.registeruser(name, "null", ie, password);
			  String statusCode = (String) data.get(code);
			  if (statusCode.equals("200")) return "注册成功";
			  return "注册失败";
			} else {
				Map<String, Object> data = userFeign.registeruser(name, ie, "null" , password);
				String statusCode = (String) data.get(code);
				if (statusCode.equals("200")) return "注册成功";
				return "注册失败";
			}
	} else {
		return "验证码已过期";
	}
  }
}
