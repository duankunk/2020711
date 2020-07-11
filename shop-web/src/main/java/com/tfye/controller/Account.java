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
import com.tfye.constants.BaseApiConstants;
import com.tfye.constants.Constants;
import com.tfye.constants.PageConstants;
import com.tfye.feign.AddressFeign;
import com.tfye.feign.OrderFeign;
import com.tfye.feign.ProductSizeFegin;
import com.tfye.feign.UserFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
@RequestMapping("/account") 
public class Account {
	
	@Autowired
	private UserFeign userFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private AddressFeign addressFeign;
	
	@RequestMapping("") 
	public ModelAndView index(HttpServletRequest request) {
		
		ModelAndView mav=new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data = userFeign.getOne( Integer.parseInt(id));
		Object user = ResultUtils.getResultMap(data);
		if (user==null) {
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		String username=null;
		if (token != null) {
			username = baseRedisService.get(id);
		}
		mav.addObject("name", username);
		mav.addObject("user",user);
		return mav; // /ftl/index.html
	}
	
	
	
	@RequestMapping("/address") 
	public ModelAndView address(HttpServletRequest request) {
		ModelAndView mav=new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data = addressFeign.PagerUid(1, 6, Integer.parseInt(id));
		Map<String, Object> useradd = (Map<String, Object>) ResultUtils.getResultMap(data);
		List<com.tfye.entity.address> userAddA = (List<com.tfye.entity.address>)useradd.get("data");
		if (userAddA==null) {
			mav.setViewName(PageConstants.ERROR);
			return mav;
		}
		System.out.println("***************************");
		System.out.println(useradd);
		System.out.println("***************************");
		mav.addObject("useradd",userAddA);
		mav.setViewName(PageConstants.ADDRESS);
		return mav; // /ftl/index.html
	}
	
	@ResponseBody
	@RequestMapping("/deleteAddressDate") 
	public String deleteAddressDate(HttpServletRequest request,@RequestParam("ad_id") String ad_id) {

		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data = addressFeign.deleteaddress(Integer.parseInt(ad_id), Integer.parseInt(id));
		Object code = data.get("code");
		if (code.equals(BaseApiConstants.HTTP_200_CODE)) return code+"";
			
		return BaseApiConstants.HTTP_500_CODE+""; // /ftl/index.html
	}
	
	@RequestMapping("/addaddress") 
	public ModelAndView addaddress() {
		ModelAndView mav=new ModelAndView();
		
		mav.setViewName(PageConstants.ADDADDRESS);
		return mav; // /ftl/index.html
	}
	@ResponseBody
	@RequestMapping("/updateIphoneEmailPassword") 
	public String updateIphoneEmailPassword( @RequestParam(value="note") String note, @RequestParam(value="code") String code,@RequestParam(value="ap") String ap) {

		
		String vi = baseRedisService.get(code);
		if (!vi.equals(note)) return "修改失败";
		String id;
		if (note.contains("@")) {
			Map<String, Object> data = userFeign.getOneEmail(note);
			LinkedHashMap<String, String> user = (LinkedHashMap<String, String>) ResultUtils.getResultMap(data);
			id = String.valueOf(user.get("id"));
		} else {
			Map<String, Object> data = userFeign.getOneIhone(note);
			LinkedHashMap<String, String> user = (LinkedHashMap<String, String>) ResultUtils.getResultMap(data);
			
			id =String.valueOf(user.get("id"));
		}
		Map<String, Object> data = userFeign.updatpassword(Integer.parseInt(id), ap);
		String co = String.valueOf(data.get("code"));
		if (co.equals( "200" )) {
			return "修改成功";
		}
		return "修改失败";
	}
	
	@ResponseBody
	@RequestMapping("/addAddressDate") 
	public String addAddressDate(HttpServletRequest request,@RequestParam("province") String province,@RequestParam("city") String city,
			@RequestParam("area") String area,@RequestParam("iphon") String iphon,@RequestParam("detailed") String detailed,
			@RequestParam("recipient") String recipient) {
		String token = CookieUtil.getUid(request, "token");
		String id = baseRedisService.get(token);
		Map<String, Object> data = addressFeign.addaddress(province, city, area, detailed, iphon, recipient, Integer.parseInt(id));
		Object code = data.get("code");
		if (code.equals(BaseApiConstants.HTTP_200_CODE)) return code+"";
		return BaseApiConstants.HTTP_500_CODE+""; // /ftl/index.html
	}
}
