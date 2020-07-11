package com.tfye.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.entity.Shopping;
import com.tfye.feign.OrderFeign;
import com.tfye.feign.ProductColorFeign;
import com.tfye.feign.ProductFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.feign.ShoppingFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
@RequestMapping("/ording") 
public class Ording {
	
	@Autowired
	private OrderFeign orderFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	
	@Autowired
	private ProductColorFeign productColorFeign;
	@Autowired
	private ProductSizeFegin productSizeFegin;
	@Autowired
	private ProductFeign productFeign;
	@Autowired
	private ShoppingFeign shoppingFeign;
	
	
	@RequestMapping("") 
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		String username=null;
		if (token != null) {;
			username = baseRedisService.get(id);
		}
		mav.addObject("name", username);
		Map<String, Object> data =shoppingFeign.getAllShopping(0, Integer.parseInt(id));
		List<Object> cartAll = (List<Object>)ResultUtils.getResultMap(data);
		String pid = "";
		String cid = "";
		String sid = "";
		for (Object shopping : cartAll) {
			LinkedHashMap<String, String> shop = (LinkedHashMap<String, String>) shopping;
			 if(cartAll.size() - 1 == cartAll.indexOf(shopping)){
				 sid =sid+ String.valueOf(shop.get("dimensions"));
				cid =cid+ String.valueOf(shop.get("scolor"));
				pid =pid+ String.valueOf(shop.get("sptiesid"));
			 }else {
				 sid =sid+ String.valueOf(shop.get("dimensions"))+",";
				cid =cid+ String.valueOf(shop.get("scolor")) +",";
				pid =pid+ String.valueOf(shop.get("sptiesid")) +",";
			}
		}
		
		Map<String, Object> data1 =productFeign.findByManyid(pid);
		List<Shopping> proAll = (List<Shopping>)ResultUtils.getResultMap(data1);
		
		Map<String, Object> data2 =productColorFeign.getAllColorPro(cid);
		List<Product_color> colAll = (List<Product_color>)ResultUtils.getResultMap(data2);
		
		Map<String, Object> data3 =productSizeFegin.getAllSizePro(sid);
		List<Product_size> sizeAll = (List<Product_size>)ResultUtils.getResultMap(data3);
		if (cartAll==null||proAll==null||colAll==null||sizeAll==null) { 
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		mav.setViewName(PageConstants.ORDING);
		return mav; // /ftl/index.html
	}
	
	
}
