package com.tfye.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Order;

@Mapper
public interface OrderDao extends BaseDao{
	//查询所有订单
	@Select("SELECT *  FROM flowershop.orderform")
	public java.util.List<Order> getAllOrder();
	
	//修改订单状态
	@Update("UPDATE flowershop.orderform SET orderstatus = #{orderstatus} WHERE id = #{id}")
	public void getUpdateOrder(@Param("orderstatus") int orderstatus,@Param("id") long id);
	
	//批量修改订单状态
//	@Select("UPDATE orderform SET orderstatus= #{updatestatus} WHERE orderstatus= #{orderstatus}")
//	public java.util.List<Order> getUpdateAllOrder(@Param("updatestatus") int updatestatus,@Param("orderstatus") int orderstatus);

	//按订单状态查询
	@Select("SELECT *  FROM flowershop.orderform where orderstatus =#{orderstatus}")
	public java.util.List<Order> getEnquiryOrder(@Param("orderstatus") int orderstatus);
	
	
	@Insert("INSERT INTO `flowershop`.`orderform`(`id`,`orderstatus`,`orderid`,`useridd`,`address`,`amount`,`tiesidquantity`,`ordernumber`,`payment`,`created`,`updated`)"
			+ "VALUES (null,#{orderstatus},#{orderid},#{useridd},#{address},#{amount},#{tiesidquantity},#{ordernumber},#{payment},#{created},#{updated})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void saveOne(@Param("o") Order o);
	

	@Select("SELECT *  FROM flowershop.orderform where useridd = #{uid}")
	public java.util.List<Order> getAllUidOrder(@Param("uid") int uid);
	
	@Update("UPDATE flowershop.orderform SET orderstatus = #{orderstatus} WHERE id = #{id} and useridd = #{uid}")
	public void getUpdateUidOrder(@Param("orderstatus") int orderstatus, @Param("id") Long id,@Param("uid") Long uid);
	
	@Select("SELECT *  FROM flowershop.orderform where orderstatus = #{orderstatus} and useridd = #{uid}")
	public java.util.List<Order> getEnquiryUidOrder(@Param("orderstatus") int orderstatus,@Param("uid") int uid);
	
	
}