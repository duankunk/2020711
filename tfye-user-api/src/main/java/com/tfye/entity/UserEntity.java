
package com.tfye.entity;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserEntity extends BaseEntity {
	
	private String uname;
	private String upassword;
	private String uphone;
	private String uemail;
	private String uopenid;
	private String uphoto;
	public String getUphoto() {
		return uphoto;
	}
	public void setUphoto(String uphoto) {
		this.uphoto = uphoto;
	}
	public String getUaddpassword() {
		return uaddpassword;
	}
	public void setUaddpassword(String uaddpassword) {
		this.uaddpassword = uaddpassword;
	}
	private String uaddpassword;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUopenid() {
		return uopenid;
	}
	public void setUopenid(String uopenid) {
		this.uopenid = uopenid;
	}
}
