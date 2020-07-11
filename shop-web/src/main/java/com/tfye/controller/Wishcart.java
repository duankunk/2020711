package com.tfye.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.runners.Parameterized.Parameter;
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
@RequestMapping("/wishcart") 
public class Wishcart {
	
	@Autowired
	private ShoppingFeign shoppingFeign;
	
	@Autowired
	private BaseRedisService baseRedisService;
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
		Map<String, Object> data =shoppingFeign.getAllShopping(4, Integer.parseInt(id));
		List<Object> wishOr = (List<Object>)ResultUtils.getResultMap(data);
		String pid = "";
		String cid = "";
		String sid = "";
		for (Object shopping : wishOr) {
			System.out.println(shopping+"ssssssssss");
			LinkedHashMap<String, String> shop = (LinkedHashMap<String, String>) shopping;
			 if(wishOr.size() - 1 == wishOr.indexOf(shopping)){
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
		if (wishOr==null||proAll==null||colAll==null||sizeAll==null) { 
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		System.out.println(colAll+"*******************");
		System.out.println(sizeAll+"*******************");
		mav.addObject("proAll",proAll);
		mav.addObject("wishAll",wishOr);
		mav.addObject("colAll",colAll);
		mav.addObject("sizeAll",sizeAll);
		mav.setViewName(PageConstants.WISHCART);
		return mav; // /ftl/index.html
	}
	
	@ResponseBody
	@RequestMapping("/dele") 
	public String dele(HttpServletRequest request,@RequestParam("o_id") String o_id) {
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data =  shoppingFeign.updateUidShopping(3, Long.parseLong(o_id), Integer.parseInt(id));
		Object code = data.get("code");
		if (code.equals("200")) return "删除成功";
		return "删除失败"; // /ftl/index.html
	}
	@ResponseBody
	@RequestMapping("/addpro") 
	public String add(HttpServletRequest request,@RequestParam("o_id") String o_id,@RequestParam("num") String num) {
		String token = CookieUtil.getUid(request, "token");
		System.out.println(o_id+"*******************"+num);
		String id = baseRedisService.get(token);
		Map<String, Object> data =  shoppingFeign.updateUidShopping(1, Long.parseLong(o_id), Integer.parseInt(id));
		Map<String, Object> data1 =  shoppingFeign.updateUidShoppingNum(Integer.parseInt(num), Long.parseLong(o_id), Integer.parseInt(id));
		Object code = data.get("code");
		Object code1 = data1.get("code");
		if (code.equals("200")&&code.equals("200")) return "添加到购物车成功";
		return "添加到购物车"; // /ftl/index.html
	}
	
	@ResponseBody
	@RequestMapping("/addnum") 
	public String addnum(HttpServletRequest request,@RequestParam("o_id") String o_id,@RequestParam("num") String num) {
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data1 =  shoppingFeign.updateUidShoppingNum(Integer.parseInt(num), Long.parseLong(o_id), Integer.parseInt(id));
		Object code1 = data1.get("code");
		if (code1.equals("200")) return "添加到购物车成功";
		return "添加到购物车"; // /ftl/index.html
	}
	
	@Autowired
	private Shopping shopping;
	@ResponseBody
	@RequestMapping("/addwish") 
	public String addwish(HttpServletRequest request,@RequestParam("pid") int pid) {
		try {
			String token = CookieUtil.getUid(request, "token");
			String id = baseRedisService.get(token);
			Map<String, Object> data1 =  shoppingFeign.save(4, Integer.parseInt(id), pid, 1, 1, 1);
			Object code1 = data1.get("code");
			
			if (code1.toString().equals("200")) return "200";
			return "失败";
		} catch (Exception e) {
			return "500";
		}
		
		 // /ftl/index.html
	}
}
