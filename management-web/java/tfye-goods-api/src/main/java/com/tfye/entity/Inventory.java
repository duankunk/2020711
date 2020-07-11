package com.tfye.entity;

import org.springframework.stereotype.Component;

import com.tfye.common.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Inventory extends BaseEntity {
	private int number;
	private int sales;
	private int inventorycol;
	private int pid;
}
