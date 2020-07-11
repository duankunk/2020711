package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Order;


@RequestMapping("/order")
public interface OrderService {
	/**
	 * 查询数据库订单所有信息
	 * @return
	 */
	@RequestMapping("/getAllorder")
	public Map<String , Object> getAllOrder();
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午5:04:18
	 * getAllOrder作用 ： (查询所有用户的订单)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getAllUserorder")
	public Map<String , Object> getAllOrder(@RequestParam("uid") int uid);
	/**
	 * 参数 Order
	 * 返回值 null
	 * 备注   保存订单订单状态0为购买 1为正在购买 2已购买3取消
	 * @param o
	 */
	@RequestMapping("/save")
	public Map<String , Object> save(@RequestParam("o") Order o);
	
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午5:08:04
	 * getUpdateOrder作用 ： (使用用户id与订单id更新状态，保存订单订单状态0为购买 1为正在购买 2已购买3取消)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/update")
	public Map<String , Object> getUpdateOrder(@RequestParam("orderstatus") 
			int orderstatus,@RequestParam("id") Long id,@RequestParam("uid") Long uid);
	
//	/**
//	 * 批量修改订单状态
//	 * orderstatus  修改前
//	 * updatestatus 修改后
//	 */
//	@RequestMapping("/updateAll")
//	public Map<String , Object> getUpdateAllOrder(@RequestParam("updatestatus") int updatestatus,@RequestParam("orderstatus") int orderstatus);
	
	/**
	 * 按照订单状态查询
	 */
	@RequestMapping("/enquiry")
	public Map<String , Object> getEnquiryOrder(@RequestParam("orderstatus") int orderstatus);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午5:05:58
	 * getEnquiryOrder作用 ： (按照用户的订单状态查找)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/enquiryUid")
	public Map<String , Object> getEnquiryOrder(@RequestParam("orderstatus") int orderstatus,@RequestParam("uid") int uid);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午7:32:31
	 * Pager作用 ： (按照用户分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/PagerUid")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit,@RequestParam("uid") int uid);
	
	/**
	 * 订单分页
	 */
	@RequestMapping("/Pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
}
