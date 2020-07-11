package com.tfye.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Product_color;
import com.tfye.entity.Product_comment;
import com.tfye.entity.Product_size;
@Mapper
public interface Product_commentDao extends BaseDao {
	//查询所有尺寸所以信息
	@Select("SELECT *  FROM product.product_comment")
	public java.util.List<Product_comment> getAllComment();
	
	
	@Insert("INSERT INTO `product`.`product_comment`(`id`,`comment`,`level`,`photo`,`over`,`created`,`updated`,`uid`)"
			+ "VALUES (null,#{comment},#{level},#{photo},#{over},#{created},#{updated},#{uid})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void savaOne(Product_comment com);
}
