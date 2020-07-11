package com.tfye.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.entity.Product;
import com.tfye.feign.OrderFeign;
import com.tfye.feign.ProductFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.utils.ResultUtils;
import com.tfye.utils.StringUtils;
import com.tfye.web.CookieUtil;

@Controller
@RequestMapping("/index") 
public class Index {
	
	@Autowired
	private ProductFeign productFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	
	
	@RequestMapping("") 
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> data = productFeign.FirstCreated();
		List<Product> newEight = (List<Product>)ResultUtils.getResultMap(data);
		Map<String, Object> data1 = productFeign.Pager(1, 6);
		Map<String, Object> ProductInfo = (Map<String, Object>)ResultUtils.getResultMap(data1);
		List<Product> topSix = (List<Product>)ProductInfo.get("data");
		if (newEight==null || topSix==null) { 
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		mav.setViewName(PageConstants.INDEX);
		mav.addObject("proNewEight",newEight);
		mav.addObject("proTopSix",topSix);
		String token = CookieUtil.getUid(request, "token");
		String username=null;
		if (token != null) {
			String userid = baseRedisService.get(token);
			username = baseRedisService.get(userid);
		}
		mav.addObject("name", username);
		return mav; // /ftl/index.html
	}
}
