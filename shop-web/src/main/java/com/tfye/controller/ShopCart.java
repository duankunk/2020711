package com.tfye.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.entity.Product;
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
@RequestMapping("/shopCart") 
public class ShopCart {
	
	@Autowired
	private BaseRedisService baseRedisService;
	
	
	@Autowired
	private ShoppingFeign shoppingFeign;
	
	@Autowired
	private ProductColorFeign productColorFeign;
	@Autowired
	private ProductSizeFegin productSizeFegin;
	@Autowired
	private ProductFeign productFeign;
	
	@RequestMapping("") 
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data =shoppingFeign.getAllShopping(1, Integer.parseInt(id));
		List<Object> cartAll = (List<Object>)ResultUtils.getResultMap(data);
		String pid = "";
		String cid = "";
		String sid = "";
		for (Object shopping : cartAll) {
			System.out.println(shopping+"ssssssssss");
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
		String username=null;
		if (token != null) {
			username = baseRedisService.get(id);
		}
		mav.addObject("name", username);
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
		System.out.println(colAll+"*******************");
		System.out.println(sizeAll+"*******************");
		mav.addObject("proAll",proAll);
		mav.addObject("cartAll",cartAll);
		mav.addObject("colAll",colAll);
		mav.addObject("sizeAll",sizeAll);
		mav.setViewName(PageConstants.SHOPCART);
		return mav; // /ftl/index.html
	}
	
	
	@RequestMapping("/pay") 
	public String addnum(HttpServletRequest request) {
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data1 =  shoppingFeign.updateAllShopping(0, id);
		Object code1 = data1.get("code");
		if (code1.equals("200")) return "forword:/ording";
		return "forword:/index"; // /ftl/index.html
	}
	
	
	
	@Autowired
	private Shopping shopping;
	@ResponseBody
	@RequestMapping("/addshop") 
	public String addwish(HttpServletRequest request,@RequestParam("pid") int pid) {
		try {
			String token = CookieUtil.getUid(request, "token");
			String id = baseRedisService.get(token);
			Map<String, Object> data1 =  shoppingFeign.save(1, Integer.parseInt(id), pid, 1, 1, 1);
			Object code1 = data1.get("code");
			if (code1.toString().equals("200")) return "添加到购物车成功";
			return "失败";
		} catch (Exception e) {
			return "500";
		}
		
		 // /ftl/index.html
	}
	
	@ResponseBody
	@RequestMapping("/addshopcart") 
	public String addshopcart(HttpServletRequest request,@RequestParam("pid") int pid,
			@RequestParam("sid") int sid,@RequestParam("cid") int cid,@RequestParam("num") int num) {
		try {
			String token = CookieUtil.getUid(request, "token");
			String id = baseRedisService.get(token);
			Map<String, Object> data1 =  shoppingFeign.save(1, Integer.parseInt(id), pid, num, sid, cid);
			Object code1 = data1.get("code");
			if (code1.toString().equals("200")) return "200";
			return "400";
		} catch (Exception e) {
			return "500";
		}
		
		 // /ftl/index.html
	}
	
	
}
