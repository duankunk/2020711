package com.tfye.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.ShoppingService;

@FeignClient(value = "ORDER")
public interface ShoppingFeign extends ShoppingService{

}
