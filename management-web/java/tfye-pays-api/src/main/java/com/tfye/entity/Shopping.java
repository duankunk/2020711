package com.tfye.entity;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shopping extends BaseEntity{
	private int sptiesid;	//购物车商品id
	private int quantity;	//数量
	private int userid;		//用户id
	private int status;		//状态，0为购买 1已购买 2正在购买
	private int scolor;		//颜色
	private int dimensions;	//尺寸
}
