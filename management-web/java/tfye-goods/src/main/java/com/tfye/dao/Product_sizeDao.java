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
public interface Product_sizeDao extends BaseDao{
	
	/*
	 * @Select("insert into Product_size(size) values(?)" public int
	 * saveAdmin(Product_size prz);
	 */
	//查询所有尺寸所以信息
	@Select("SELECT *  FROM product.product_size")
	public java.util.List<Product_size> getAllSize();
	
	@Insert("INSERT INTO `product`.`product_size`(`id`,`size`,`created`,`updated`) VALUES (null,#{size},#{created},#{updated})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void savaOne(Product_size size);
	
	
	@Select({
        "<script>",
            "SELECT *  FROM `product`.`product_size` where id in",
            "<foreach collection='size' item='item' open='(' separator=',' close=')'>",
                "#{item}",
            "</foreach>",
        "</script>"
})
	public java.util.List<Product_size> getAllSizePro(@Param("size") List<String> size);
}
