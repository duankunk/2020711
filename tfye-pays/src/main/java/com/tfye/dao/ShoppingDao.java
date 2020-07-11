package com.tfye.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Order;
import com.tfye.entity.Shopping;

@Mapper
public interface ShoppingDao extends BaseDao{
	//查询所有订单购物车所以信息
	@Select("SELECT *  FROM flowershop.shopping")
	public java.util.List<Shopping> getAllShopping();
	//修改订单状态
	@Update("UPDATE flowershop.shopping SET `status` = #{status} WHERE id = #{id}")
	public void updateShopping(@Param("status") int status,@Param("id") long id);
	//批量修改订单状态
	
	@Insert("INSERT INTO `flowershop`.`shopping`(id,sptiesid,quantity,userid,status,scolor,dimensions,created,updated)"
			+ "VALUES (null,#{sptiesid},#{quantity},#{userid},#{status},#{scolor},#{dimensions},#{created},#{updated})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void saveOne(Shopping s);
	@Update({
        "<script>",
        "UPDATE flowershop.shopping SET `status`= #{updatestatus} where id in",
        "<foreach collection='size' item='item' open='(' separator=',' close=')'>",
            "#{item}",
        "</foreach>",
    "</script>"
})
	public void updateManyShopping(@Param("updatestatus") int updatestatus,@Param("status") List<String> status);
	
	
	@Update("UPDATE flowershop.shopping SET `status` = #{updatestatus} WHERE userid = #{uid}")
	public void updateAllShopping(@Param("updatestatus") int updatestatus,@Param("uid") String uid);
	
	
	
	
	@Select("SELECT *  FROM flowershop.shopping where `status` =#{status}")
	public java.util.List<Shopping> getEnquiryShopping(@Param("status") int status);
	@Select("SELECT *  FROM flowershop.shopping where `status` =#{status} and userid= #{userid}")
	public java.util.List<Shopping> getEnquiryUidShopping(@Param("status") int status, @Param("userid")  int userid);
	@Update("UPDATE flowershop.shopping SET `status` = #{status} WHERE id = #{id} and userid= #{userid} ")
	public void updateUidShopping(@Param("status") int status, @Param("id") Long id, @Param("userid") int userid);
	@Select("SELECT *  FROM flowershop.shopping where status=#{status} and userid= #{userid}")
	public java.util.List<Shopping>  getAllUidShopping(@Param("status") int status, @Param("userid") int userid);
	
	@Update("UPDATE flowershop.shopping SET `quantity` = #{num} WHERE id = #{id} and userid= #{userid} ")
	public void updateUidShoppingNum(@Param("num") int num, @Param("id") Long id, @Param("userid") int userid);
}
