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
import com.tfye.entity.Product_comment;
import com.tfye.feign.ProductCommentFeign;
import com.tfye.utils.ResultUtils;
import com.tfye.web.CookieUtil;

@Controller
public class Comment {
	@Autowired
	private ProductCommentFeign productCommentFeign;
	@Autowired
	private BaseRedisService baseRedisService;
	@RequestMapping("/comment")
	public ModelAndView address(HttpServletRequest request,@RequestParam(value="pager" ,defaultValue="0") int pager,@RequestParam(value="page" ,defaultValue="10") int page) {
		ModelAndView mav = new ModelAndView();
		String token = CookieUtil.getUid(request, "token");
		String ps = baseRedisService.get(token);
		if (!ps.equals("root")) {
			mav.setViewName("404");	
			return mav;
		}
		Map<String, Object> commentInfo = (Map<String, Object>)productCommentFeign.Pager(pager, page);
		Map<String, Object> comment = (Map<String, Object>)ResultUtils.getResultMap(commentInfo);
		List<Product_color> commentall = (List<Product_color>)comment.get("data");
		int count = Integer.parseInt(comment.get("count").toString());
		int p = count/10;
		mav.addObject("commentall", commentall);
		mav.addObject("count", p);
		mav.setViewName("comment");	
		return mav;
//		v  comment
	}
}
