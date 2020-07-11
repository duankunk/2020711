package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Product;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;

@RequestMapping("/color")
public interface Product_colorService {
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:02:11
	 * getAllColor作用 ： (得到所有颜色)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getAllColor")
	public Map<String, Object> getAllColor();
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:01:51
	 * saveAdmin作用 ： (插入颜色 )
	 * 返回类型 ：Map<String,Object>
	 * 参数为一个颜色
	 */
	@RequestMapping("/add")
	public Map<String, Object> saveAdmin(@RequestParam("color") String color);
	
	/**
	 *颜色 分页
	 */
	@RequestMapping("/pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
	
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:02:11
	 * getAllColor作用 ： (得到所有颜色)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getAllColorPro")
	public Map<String, Object> getAllColorPro(@RequestParam("color") String color);

}
