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
import com.tfye.feign.OrderFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class OrderController {
	@Autowired
	private OrderFeign orderFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	@RequestMapping("/order")
	public ModelAndView address(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> orderInfo = (Map<String, Object>)orderFeign.Pager(pager, page);
		Map<String, Object> order = (Map<String, Object>)ResultUtils.getResultMap(orderInfo);
		List<com.tfye.entity.Order> orderall = (List<com.tfye.entity.Order>)order.get("data");
		int count = Integer.parseInt(order.get("count").toString());
		int p = count/10;
		mav.addObject("ordertInfo", orderall);
		mav.addObject("count", p);
		mav.setViewName("order");	
		return mav;
//		v  order
	}
}
