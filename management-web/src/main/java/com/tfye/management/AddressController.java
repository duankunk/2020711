package com.tfye.management;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tfye.common.redis.BaseRedisService;
import com.tfye.entity.Product;
import com.tfye.feign.AddressFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class AddressController {
	@Autowired
	private AddressFeign addressFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	@RequestMapping("/address")
	public ModelAndView address(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> addressInfo = (Map<String, Object>)addressFeign.Pager(pager, page);
		Map<String, Object> address = (Map<String, Object>)ResultUtils.getResultMap(addressInfo);
		List<com.tfye.entity.address> addressall = (List<com.tfye.entity.address>)address.get("data");
		int count = Integer.parseInt(address.get("count").toString());
		int p = count/10;
		mav.addObject("address", addressall);
		mav.addObject("count", p);
		mav.setViewName("address");	
		return mav;
	}
}
