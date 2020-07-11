package com.tfye.management;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.utils.MD5Util;
import com.tfye.web.CookieUtil;

@Controller
public class Login {
	 final String USER = "root" ;
	 final String PASSWORD = "root" ;
	 
	@RequestMapping("")
	public String index() {
			
		return "login";
//		product
	}
	
	@RequestMapping("/loginerror")
	public String error() {
			
		return "404";
//		product
	}
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	@ResponseBody
	@RequestMapping("/loginuser")
	public String login(HttpServletResponse response,@RequestParam(value="p") String p,@RequestParam(value="u") String u) {
		if (p.equals(USER)&&u.equals(PASSWORD)) {
			String key = MD5Util.MD5(u);
			baseRedisService.set(key, p, Long.parseLong("14400"));
			CookieUtil.addCookie(response, "token", key, 14400);
			return "200";
		}
		return "500";
//		product
	}
}
