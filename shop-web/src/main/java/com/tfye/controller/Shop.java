package com.tfye.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.entity.Product;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.feign.ProductColorFeign;
import com.tfye.feign.ProductFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@RequestMapping("/shop")
@Controller
public class Shop {
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private ProductColorFeign productColorFeign;
	@Autowired
	private ProductSizeFegin productSizeFegin;
	@Autowired
	private ProductFeign productFeign;
	@RequestMapping("")
	public ModelAndView shop(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager) {
		ModelAndView mav=new ModelAndView();
//		Map<String, Object> data = productColorFeign.getAllColor();
//		List<Product_color> allColor = (List<Product_color>)ResultUtils.getResultMap(data);
//		
//		Map<String, Object> data1 = productSizeFegin.getAllSize();
//		List<Product_size> allSize = (List<Product_size>)ResultUtils.getResultMap(data1);
		
		Map<String, Object> data2 = productFeign.Pager(pager, 12);
		
		Map<String, Object> ProductInfo = (Map<String, Object>)ResultUtils.getResultMap(data2);
		List<Product> Product = (List<Product>)ProductInfo.get("data");
		int count = Integer.parseInt(ProductInfo.get("count").toString());
		int p = count/10;
		System.out.println(Product);
		System.out.println("**********************************************************");
		if (ProductInfo==null) { 
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		String token = CookieUtil.getUid(request, "token");
		String username=null;
		if (token != null) {
			String userid = baseRedisService.get(token);
			username = baseRedisService.get(userid);
		}
		mav.addObject("name", username);
		mav.setViewName("shop");
//		mav.addObject("allcolor", allColor);
//		mav.addObject("allsize", allSize);
		mav.addObject("allproduct", Product);
		mav.addObject("count", p);
		mav.addObject("now", pager);
		return mav;
	}
	
	
}
