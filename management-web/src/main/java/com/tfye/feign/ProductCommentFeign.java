package com.tfye.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.Product_commentService;

@FeignClient(value = "GOODS")
public interface ProductCommentFeign extends Product_commentService{

}
