package com.tfye.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.tfye.api.service.OrderService;
import com.tfye.common.api.BaseApiService;
import com.tfye.entity.Order;

@FeignClient(value = "ORDER")
public interface OrderFeign extends OrderService{

}
