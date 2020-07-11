package com.tfye.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.Product_colorService;

@FeignClient(value = "GOODS")
public interface ProductColorFeign extends Product_colorService{

}
