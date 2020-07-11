package com.tfye.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.Order;
import com.tfye.entity.Shopping;


@RequestMapping("/shopping")
public interface ShoppingService {
	
	/**
	 * 查询数据库购物车所有信息
	 * @return
	 */
	@RequestMapping("/getAllshopping")
	public Map<String , Object> getAllShopping();
	
	/**
	 * 参数 Shoppping
	 * 返回值 null
	 * 备注   保存订单订单状态0为购买 1为正在购买 2已购买3取消
	 * @param o
	 */
	@RequestMapping("/save")
	public Map<String , Object> save(@RequestParam("s") Shopping s);
	

	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午4:29:08
	 * updateShopping作用 ： (更新用户购物车或者心愿单一个商品的状态 状态0为购买 1为正在购买 2已购买3取消	)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/update")
	public Map<String , Object> updateShopping(@RequestParam("status") int status,@RequestParam("id") Long id);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午4:08:10
	 * getUpdateManyShopping作用 ： (状态批量更改购物车或者心愿单所有的状态)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updateMany")
	public Map<String , Object> updateManyShopping(@RequestParam("updatestatus") int updatestatus,@RequestParam("id") String id);
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午4:09:33
	 * getUpdateAllShopping作用 ： (更新用户购物车或者心愿单所有的状态)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updateAll")
	public Map<String , Object> updateAllShopping(@RequestParam("updatestatus") int updatestatus,@RequestParam("userid") String userid);
	
	/**
	 * 按照购物车购买状态查询
	 */
	@RequestMapping("/enquiry")
	public Map<String , Object> getEnquiryShopping(@RequestParam("status") int status);
	
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午6:54:29
	 * getEnquiryUidShopping作用 ： (按照用户查找购物商品)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/enquiryUid")
	public Map<String , Object> getEnquiryUidShopping(@RequestParam("status") int status,@RequestParam("userid") int userid);
	
	/**
	 *购物车 分页
	 */
	@RequestMapping("/Pager")
	public Map<String , Object> Pager(@RequestParam("page") int page,@RequestParam("limit") int limit);
	
	
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午4:29:08
	 * updateShopping作用 ： (更新用户购物车或者心愿单一个商品的状态 状态0为购买 1为正在购买 2已购买3取消	)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updateUid")
	public Map<String , Object> updateUidShopping(@RequestParam("status") int status,@RequestParam("id") Long id,@RequestParam("userid") int userid);

	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午6:59:30
	 * PagerUid作用 ： (根据用户进行分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/PagerUid")
	public Map<String , Object> PagerUid(@RequestParam("page") int page,@RequestParam("limit") int limit,@RequestParam("userid") int userid,@RequestParam("status") int status);


	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 下午7:00:51
	 * getAllShopping作用 ： (查询所有的用户购物单)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/getAllUidshopping")
	public Map<String , Object> getAllShopping(@RequestParam("status") int status,@RequestParam("userid") int userid);
}
