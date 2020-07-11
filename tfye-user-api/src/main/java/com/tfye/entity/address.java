
	
package com.tfye.entity;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class address extends BaseEntity  {
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String province;
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}

	private String town;
	private String area;
	private String cellphone;
	private String address;
	private String name;
	private int aid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	private String uid;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
}
