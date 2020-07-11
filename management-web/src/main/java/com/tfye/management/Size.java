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
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class Size {
	@Autowired
	private ProductSizeFegin productSizeFegin;
	@Autowired
	private BaseRedisService baseRedisService;
	
	@RequestMapping("/size")
	public ModelAndView product(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> sizeInfo = (Map<String, Object>)productSizeFegin.Pager(pager, page);
		Map<String, Object> size = (Map<String, Object>)ResultUtils.getResultMap(sizeInfo);
		List<Product_size> sizeall = (List<Product_size>)size.get("data");
		int count = Integer.parseInt(size.get("count").toString());
		int p = count/10;
		mav.addObject("sizeall", sizeall);
		mav.addObject("count", p);
		mav.setViewName("size");	
		return mav;
//		 size
	}
	
	@ResponseBody
	@RequestMapping("/addsize")
	public String add(@RequestParam(value="size") String size) {
		Map<String, Object> productInfo = (Map<String, Object>)productSizeFegin.saveAdmin(size);
		Object code = productInfo.get("code");
		return "200";
	}
	
	@RequestMapping("/sizeadd")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sizeadd");	
		return mav;
//		product
	}
	
}
