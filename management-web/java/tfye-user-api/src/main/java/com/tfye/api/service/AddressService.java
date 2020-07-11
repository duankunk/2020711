
package com.tfye.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tfye.entity.address;


@RequestMapping("/address")
public interface AddressService {
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:07:35
	 * deleteaddress作用 ： (更具用户id与地址id删除地址)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/deleteaddress")
	public Map<String , Object> deleteaddress(@RequestParam("id") int id,@RequestParam("uid") int uid);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:08:03
	 * updateaddress作用 ： (根据address更新地址)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updateaddress")
	public Map<String , Object> updateaddress(@RequestParam("id") int id,@RequestParam("province") String province,@RequestParam("town") String town,
			@RequestParam("area") String area,@RequestParam("address") String address,@RequestParam("cellphone") String cellphone,
			@RequestParam("name") String name,@RequestParam("uid") int uid);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:08:30
	 * addaddress作用 ： (根据address添加地址)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/addaddress")
	public Map<String , Object> addaddress(@RequestParam("province") String province,@RequestParam("town") String town,
			@RequestParam("area") String area,@RequestParam("address") String address,@RequestParam("cellphone") String cellphone,
			@RequestParam("name") String name,@RequestParam("uid") int uid);
    /**
     * ַ
     * 作者 ： kun
     * 编辑日期 ： 上午9:09:05
     * selectnum作用 ： (查询用户所有地址)
     * 返回类型 ：Map<String,Object>
     */
	@RequestMapping("/selectnum")
	public Map<String , Object> selectnum(String uid);
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:09:28
	 * getAlladdress作用 ： (得到所有地址)
	 * 返回类型 ：List<address>
	 */
	@RequestMapping("/getAlladdress")
	public Map<String , Object> getAlladdress();
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:10:21
	 * Pager作用 ： (更具用户地址分页)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/PagerUid")
	public Map<String , Object> PagerUid(@RequestParam("page") int page,@RequestParam("limit") int limit,@RequestParam("uid") int uid);
	
	
	/**
	 * 
	 * 作者 ： kun
	 * 编辑日期 ： 上午9:13:58
	 * updateaddress作用 ： (更改默认地址，id为设置成默认，ido为取消默认)
	 * 返回类型 ：Map<String,Object>
	 */
	@RequestMapping("/updateaddressStatus")
	public Map<String , Object> updateaddress(@RequestParam("id") int id,@RequestParam("uid") int uid,@RequestParam("ido") int ido);
}
