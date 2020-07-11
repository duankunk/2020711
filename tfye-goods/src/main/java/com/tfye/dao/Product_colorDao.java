package com.tfye.dao;



import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_size;
@Mapper
public interface Product_colorDao extends BaseDao{
	
	/*
	 * @Select("insert into product_color(color) values(prc=#{prc})") public int
	 * saveAdmin(Product_color prc);
	 */
	//查询所有颜色所以信息
	@Select("SELECT *  FROM product.product_color")
	public java.util.List<Product_color> getAllColor();
	
	@Insert("INSERT INTO product.product_color(color,id,created,updated) VALUES (#{color},null,#{created},#{updated})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void savaOne(Product_color col);
	
//	@Select("SELECT *  FROM product.product_color where id in (#{color})")
	
	@Select({
        "<script>",
            "SELECT *  FROM product.product_color where id in",
            "<foreach collection='color' item='item' open='(' separator=',' close=')'>",
                "#{item}",
            "</foreach>",
        "</script>"
})
	public java.util.List<Product_color> getAllColorPro(@Param("color") List<String> color);
}
