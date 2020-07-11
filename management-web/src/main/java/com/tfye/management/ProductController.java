package com.tfye.management;

import java.sql.Timestamp;
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
import com.tfye.feign.ProductColorFeign;
import com.tfye.feign.ProductFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class ProductController {
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private ProductFeign productFeign;
	@Autowired
	private ProductColorFeign productColorFeign;
	@Autowired
	private ProductSizeFegin productSizeFegin;
	@RequestMapping("/product")
	public ModelAndView product(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> productInfo = (Map<String, Object>)productFeign.Pager(pager, page);
		Map<String, Object> product = (Map<String, Object>)ResultUtils.getResultMap(productInfo);
		List<Product> productall = (List<Product>)product.get("data");
		int count = Integer.parseInt(product.get("count").toString());
		int p = count/10;
		mav.addObject("productall", productall);
		mav.addObject("count", p);
		mav.setViewName("product");	
		return mav;
//		product
	}
	@ResponseBody
	@RequestMapping("/add")
	public String add(@RequestParam(value="phone") String phone,@RequestParam(value="name") String name,
			@RequestParam(value="price") String price,@RequestParam(value="dis") String dis,@RequestParam(value="scri") String scri,
			@RequestParam(value="size") String size,@RequestParam(value="color") String color) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> productInfo = (Map<String, Object>)productFeign.saveAdmin(phone, name, price, Double.parseDouble(dis), scri,"1", color, size);
		Object code = productInfo.get("code");
		if (code.toString().equals("200")) { return "200";}
		return "500";
//		product
	}
	
	@RequestMapping("/proadd")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> data = productColorFeign.getAllColor();
		List<Product_color> allColor = (List<Product_color>)ResultUtils.getResultMap(data);
		Map<String, Object> data1 = productSizeFegin.getAllSize();
		List<Product_size> allSize = (List<Product_size>)ResultUtils.getResultMap(data1);
		mav.addObject("allcolor", allColor);
		mav.addObject("allsize", allSize);
		mav.setViewName("proadd");	
		return mav;
//		product
	}
	
}
