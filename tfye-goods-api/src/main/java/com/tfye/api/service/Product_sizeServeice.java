package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Product_size;

@RequestMapping("/size")
public interface Product_sizeServeice {
	/**
	 * 查询数据库尺寸所有信息
	 * @return
	 */
	@RequestMapping("/getAllSize")
	public Map<String , Object> getAllSize();
	
	/**
	 * 添加尺寸
	 * @param prc
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String , Object> saveAdmin(@RequestParam("size") String size);
	/**
	 *尺寸 分页
	 */
	@RequestMapping("/pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
	
	@RequestMapping("/getAllSizePro")
	public Map<String, Object> getAllSizePro(@RequestParam("size") String size);
}
