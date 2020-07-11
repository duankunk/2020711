package com.tfye.dao;

import java.awt.List;
import java.sql.Timestamp;

import javax.mail.internet.AddressException;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.address;
@Mapper
public interface AddressDao extends BaseDao {
	    //����idɾ����ַ
		@Delete("delete from test.address where id= #{id} and uid=#{uid}")
		public void deleteaddress(@Param("id") int id,@Param("uid") int uid);
	     //�û��޸ĵ�ַ
		@Update("update test.address set province=#{province},town=#{town},`area`=#{area},address=#{address},cellphone=#{cellphone}"
				+ ",name=#{name},updated=#{updated} where id=#{id} and uid=#{uid}")
		public void updateaddress( 
				@Param("id")int id,@Param("province")String province,@Param("town")String town,
				@Param("area")String area,
				@Param("address")String address,@Param("cellphone")String cellphone,
				@Param("name")String name,@Param("uid") int uid,@Param("updated") Timestamp updated);
		
		//���ӵ�ַ
		@Insert("insert into test.address(province,town,`area`,cellphone,address,name,aid,uid,updated,created) values "
				+ "(#{province},#{town},#{area},#{cellphone},#{address},#{name},0,#{uid},#{updated},#{created})")
		public void addaddress(@Param("province")String province,@Param("town")String town,
				@Param("area")String area,@Param("cellphone")String cellphone,@Param("address")String address
				,@Param("name")String name,@Param("uid")int uid,@Param("updated") Timestamp updated,@Param("created") Timestamp created);
       
		@Insert("insert into test.address(province,town,`area`,cellphone,address,name,aid,uid,updated,created) values "
				+ "(#{province},#{town},#{area},#{cellphone},#{address},#{name},1,#{uid},#{updated},#{created})")
		public void addaddress1(@Param("province")String province,@Param("town")String town,
				@Param("area")String area,@Param("cellphone")String cellphone,@Param("address")String address
				,@Param("name")String name,@Param("uid")int uid,@Param("updated") Timestamp updated,@Param("created") Timestamp created);
       
		//����
		@Select("select * from test.address where uid=#{uid}")
		public java.util.List<address> selectnum(@Param("uid") String uid);
		//��ѯ��������
		@Select("select * from test.address")
		public java.util.List<address> getAlladdress();
		
		@Update("update test.address set aid = 0 where id=#{ido} and uid=#{uid}")
		public java.util.List<address> updateaddressStatus(@Param("ido") int ido,@Param("uid") int uid);
		
		@Update("update test.address set aid = 1 where id=#{ido} and uid=#{uid}")
		public java.util.List<address> updateaddressStatus1(@Param("uid") int uid,@Param("ido")  int ido);
		
		
		
		}