package com.tfye.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.Product;
import com.tfye.entity.Product_size;



@Mapper
public interface productDao extends BaseDao {

	
	@Select("select * from product.product  WHERE id=#{id}")
	Product findById(@Param("id") int id);
	
	@Select("select * from product.product")
	List<Product> getProductAll();
	
//	@Select("SELECT * FROM product.product ORDER BY sales DESC LIMIT 4")
//	List<Product> Firstsales();
	
	@Select("SELECT * FROM product.product ORDER BY created DESC LIMIT 8")
	List<Product> FirstCreated();
	
	@Update("UPDATE product.product SET updated=#{updated},over=#{over}  WHERE id = #{id}")
	public void UpdataProduct(Product pro);
	
	@Insert("INSERT INTO `product`.`product`(`id`,`photo`,`name`,`price`,`discount`,`describe`,`over`,`color`,`size`,`overtime`,`created`,`updated`)"
			+ " VALUES (null,#{photo},#{name},#{price},#{discount},#{describe},#{over},#{color},#{size},#{overtime},#{created},#{updated})")
	@Options(useGeneratedKeys = true, keyProperty = "id") 
	public void savaOne(Product pro);
	
	@Delete("delete from product.product where id=#{id}")
	public void deleteProductById(int id);
	
	
	@Select({
        "<script>",
            "SELECT *  FROM product.product where id in",
            "<foreach collection='id' item='item' open='(' separator=',' close=')'>",
                "#{item}",
            "</foreach>",
        "</script>"
	})
	public java.util.List<Product> getAllProductString(@Param("id") List<String> id);

	
}
