package com.tfye.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Product_color extends BaseEntity {

	private	String color;

}
