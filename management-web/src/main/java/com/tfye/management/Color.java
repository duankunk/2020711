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
import com.tfye.entity.Product_color;
import com.tfye.feign.ProductColorFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class Color {
	@Autowired
	private ProductColorFeign productColorFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	
	@RequestMapping("/color")
	public ModelAndView address(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> colorInfo = (Map<String, Object>)productColorFeign.Pager(pager, page);
		Map<String, Object> color = (Map<String, Object>)ResultUtils.getResultMap(colorInfo);
		List<Product_color> colorall = (List<Product_color>)color.get("data");
		int count = Integer.parseInt(color.get("count").toString());
		int p = count/10;
		mav.addObject("colorall", colorall);
		mav.addObject("count", p);
		mav.setViewName("color");	
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/addcolor")
	public String add(@RequestParam(value="size") String size) {
		Map<String, Object> productInfo = (Map<String, Object>)productColorFeign.saveAdmin(size);
		Object code = productInfo.get("code");
		return "200";
	}
	
	@RequestMapping("/coloradd")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coloradd");	
		return mav;
//		product
	}
}
