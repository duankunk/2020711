package com.tfye.entity;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends BaseEntity{
	private int orderstatus;	//订单状态
	private String orderid;	//订单编号
	private int useridd;	//用户
	private int address;	//地址
	private double amount;		//金额
	private String tiesidquantity;		//商品id+数量
	private String ordernumber; //支付包订单编号
	private String payment;		//支付类型
}
