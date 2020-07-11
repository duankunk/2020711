package com.tfye.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.Product_sizeServeice;

@FeignClient(value = "GOODS")
public interface ProductSizeFegin extends Product_sizeServeice{

}
