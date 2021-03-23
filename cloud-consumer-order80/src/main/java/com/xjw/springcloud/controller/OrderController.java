package com.xjw.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.xjw.springcloud.entities.CommonResult;
import com.xjw.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

  //  public static final String PAYMENT_URL = "http://localhost:8001/";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE/";



    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/comsumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("入参:{}", JSONObject.toJSONString(payment));
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/comsumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id){
        log.info("入参:{}", JSONObject.toJSONString(id));
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
