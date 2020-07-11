package com.tfye.api.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Product;

@RequestMapping("/product")
public interface ProductService {

	/**
	 * 查询商品
	 * @return
	 */
	@RequestMapping("/findByid")
	public Map<String , Object> findByid(@RequestParam("id") int id);
	
	/**
	 * 作者 ： kun
	 * 编辑日期 ： 下午12:23:45
	 * findByid作用 ： (查询所有数据)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/findByidAllInfo")
	public Map<String , Object> findByidAllInfo(@RequestParam("id") int id);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午2:26:27
	 * findByManyid作用 ： (查询多个商品id)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/findByManyid")
	public Map<String , Object> findByManyid(@RequestParam("id") String id);
	
	/**
	 * 显示所有商品
	 */
	@RequestMapping("/getAll")
	public Map<String , Object> getProductAll();
	/**
	 * 添加商品
	 */
	@RequestMapping("/add")
	public Map<String , Object> saveAdmin(@RequestParam("pro") Product pro);
//	/**
//	 * 查询销量第一
//	 * @param pro
//	 * @return
//	 */
//	@RequestMapping("/sales")
//	public Map<String , Object> Firstsales();
	/**
	 * 查询最新商品
	 * @return
	 */
	@RequestMapping("/created")
	public Map<String , Object> FirstCreated();
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午2:44:55
	 * UpdataProduct作用 ： (更改商品是否显示 传入商品id与over显示状态)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updata")
	public Map<String , Object> UpdataProductOver(@RequestParam("pro") Product pro);

	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午2:37:33
	 * Pager作用 ： (产品分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
}
