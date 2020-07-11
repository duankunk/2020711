package com.tfye.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Product_comment extends BaseEntity {
	private String comment;
	private String level;
	private String photo;
	private int over;
	private int uid;
}
