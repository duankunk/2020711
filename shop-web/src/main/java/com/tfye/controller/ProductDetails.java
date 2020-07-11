package com.tfye.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.constants.PageConstants;
import com.tfye.entity.Inventory;
import com.tfye.entity.Product;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.feign.ProductColorFeign;
import com.tfye.feign.ProductFeign;
import com.tfye.feign.UserFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class ProductDetails {
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private ProductFeign productFeign;
	@Autowired
	private ProductColorFeign productColorFeign;
	@RequestMapping("/productdetails/{id}")
	public ModelAndView productDetail(HttpServletRequest request,@PathVariable int id) {
		ModelAndView mav=new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String username=null;
		if (token != null) {
			String userid = baseRedisService.get(token);
			username = baseRedisService.get(userid);
		}
		mav.addObject("name", username);
		Map<String, Object> proInfo = productFeign.findByidAllInfo(id);
		Map<String, Object> ProductInfo = (Map<String, Object>)ResultUtils.getResultMap(proInfo);
		Map<String, Object> data1 = productFeign.Pager(1, 6);
		Map<String, Object> ProductInfoSixInfo = (Map<String, Object>)ResultUtils.getResultMap(data1);
		System.out.println(ProductInfoSixInfo+"*******************************");
		List<Product> productInfoSix = (List<Product>)ProductInfoSixInfo.get("data");
		System.out.println("*******************************");
		System.out.println(productInfoSix+"*******************************");
		if (ProductInfo==null||ProductInfoSixInfo==null) {
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		List<Product> topSix = (List<Product>)ProductInfo.get("data");;
		LinkedHashMap<String, String> pro = (LinkedHashMap<String, String>)ProductInfo.get("pro");
		List<Product_size> size = (List<Product_size>)ProductInfo.get("size");
		List<Inventory> inventoryDao = (List<Inventory>)ProductInfo.get("inventoryDao");
		List<Product_color> color = (List<Product_color>)ProductInfo.get("color");
		mav.setViewName(PageConstants.PRODUCTDETAILS);
		mav.addObject("pro", pro);
		mav.addObject("size", size);
		mav.addObject("inventoryDao", inventoryDao);
		mav.addObject("color", color);
		mav.addObject("productInfoSix", productInfoSix);
		return mav;
	}
}
