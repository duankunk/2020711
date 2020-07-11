package com.tfye.manage;


import java.util.List;
import java.util.Map;

import com.tfye.entity.Product_color;
import com.tfye.entity.Product_comment;

public interface Product_commentManager {
	/**
	 * 增加评论
	 * @param prm
	 * @return
	 */
	public Map<String, Object> saveAdmin(Product_comment prm);
	/**
	 * 查询评论
	 * @return
	 */
	public List<Product_comment> getAllComment();
	
	/**
	 * 分页
	 * @param page
	 * @param limit
	 * @return
	 */
	public Map<String , Object> Pager(int page,int limit);
}
