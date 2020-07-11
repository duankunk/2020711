package com.tfye.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tfye.common.mybatis.BaseDao;
import com.tfye.entity.UserEntity;
import com.tfye.entity.address;

@Mapper
public interface UserDao extends BaseDao {
	//����id����Ϣ
	@Select("select * from test.user where id= #{id}")
	public UserEntity getOne(@Param("id") int id);
	
	@Select("select * from test.user where uemail= #{uemail}")
	public UserEntity getUEmail(@Param("uemail") String uemail);
	
	@Select("select * from test.user where uphone = #{uphone}")
	public UserEntity getUphone(@Param("uphone") String uphone);
	
	@Select("select * from test.user where uname= #{uname}")
	public UserEntity getUName(@Param("uname") String uname);
     //�û��޸�����
	@Update("update test.user set upassword=#{password},updated=#{updated} where id=#{id}")
	public void updatepassword(@Param("id")int id, @Param("password")String password,@Param("updated") Timestamp updated);
	//�û�ע��
	
	@Insert("insert into test.user(uname,uphone,upassword,uaddpassword,created,updated)"
			+ " values (#{uname},#{uphone},#{upassword},#{uaddpassword},#{created},#{updated})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void registeruseruphone(@Param("uname")String uname, @Param("uphone")String uphone,
			@Param("upassword")String upassword, 
			@Param("uaddpassword")String uaddpassword,@Param("created") Timestamp created,@Param("updated") Timestamp updated);
	
	@Insert("insert into test.user(uname,uemail,upassword,uaddpassword,created,updated)"
			+ " values (#{uname},#{uemail},#{upassword},#{uaddpassword},#{created},#{updated})")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void registeruseruemail(@Param("uname")String uname, 
			@Param("uemail")String uemail, @Param("upassword")String upassword, 
			@Param("uaddpassword")String uaddpassword,@Param("created") Timestamp created,@Param("updated") Timestamp updated);
	
	//�ϴ�ͼƬ��·��	
	
	@Update("update test.user set uphoto=#{photo} where id=#{id}")
	public void updateimg(@Param("photo")String photo,@Param("id") int id);
	//��ѯ��������
	@Select("select * from test.user")
	public java.util.List<UserEntity> getAlluser();
	//��¼
	@Select("select * from test.user where uphone=#{uname} and upassword=#{password}")
	public UserEntity deng(@Param("uname")String uname,@Param("password")String password);

	
	

}
