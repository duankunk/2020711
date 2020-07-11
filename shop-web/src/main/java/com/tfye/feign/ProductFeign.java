package com.tfye.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.ProductService;

@FeignClient(value = "GOODS")
public interface ProductFeign extends ProductService{

}
