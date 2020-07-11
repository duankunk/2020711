package com.tfye.management;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.entity.Product;
import com.tfye.entity.UserEntity;
import com.tfye.feign.UserFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class User {
	@Autowired
	private UserFeign userFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	
	@RequestMapping("/user")
	public ModelAndView product(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> userInfo = (Map<String, Object>)userFeign.Pager(pager, page);
		Map<String, Object> user = (Map<String, Object>)ResultUtils.getResultMap(userInfo);
		List<UserEntity> userall = (List<UserEntity>)user.get("data");
		int count = Integer.parseInt(user.get("count").toString());
		int p = count/10;
		mav.addObject("userall", userall);
		mav.addObject("count", p);
		mav.setViewName("user");	
		return mav;
//		 user
	}
	
	
}
