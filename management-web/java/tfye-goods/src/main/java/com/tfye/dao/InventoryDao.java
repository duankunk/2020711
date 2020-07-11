package com.tfye.dao;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Inventory;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
@Mapper
public interface InventoryDao extends BaseDao{
	
	/*
	 * @Select("insert into product_color(color) values(prc=#{prc})") public int
	 * saveAdmin(Product_color prc);
	 */
	//查询所有颜色所以信息
	@Select("SELECT * FROM product.inventory where pid=#{pid}")
	public Inventory getOneInventory(String pid);
	
	@Select("SELECT * FROM product.inventory")
	public java.util.List<Inventory> getAllInventory();
	
	@Insert("INSERT INTO product.inventory(`id`,`created`,`updated`,`sales`,`inventorycol`,`pid`)"
			+ " VALUES (null,#{created},#{updated},#{sales},#{inventorycol},#{pid})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void saveOne(Inventory inventory);
	
//	@Select("SELECT *  FROM product.product_color where id in (#{color})")
	
	
}
