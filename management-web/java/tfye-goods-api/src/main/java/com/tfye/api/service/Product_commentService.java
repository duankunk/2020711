package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Product_comment;
import com.tfye.entity.Product_size;
@RequestMapping("/comment")
public interface Product_commentService {
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:52:34
	 * getAllComment作用 ： (查询数据库评论所有信息)
	 * 返回类型 ：List<Product_comment>
	 */
	@RequestMapping("/getAllComment")
	public Map<String, Object> getAllComment();
	/**
	 * 添加评论
	 * @param prm
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> saveAdmin(@RequestParam("prm") Product_comment prm);
	
	/**
	 *尺寸 分页
	 */
	@RequestMapping("/pager")
	public Map<String, Object> Pager(@RequestParam("page") int page,@RequestParam("limit")  int limit);
	
}
