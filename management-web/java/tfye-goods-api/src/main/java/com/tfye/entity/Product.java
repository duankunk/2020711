package com.tfye.entity;



import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Product extends BaseEntity {
	private String photo;
	private String name;
	private String price;
	private double discount;
	private String describe;
	private int over;
	private String color;
	private String size;
	private Timestamp overtime;

	
}
