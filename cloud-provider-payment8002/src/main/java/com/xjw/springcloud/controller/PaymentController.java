package com.xjw.springcloud.controller;

import com.xjw.springcloud.entities.CommonResult;
import com.xjw.springcloud.entities.Payment;
import com.xjw.springcloud.service.PaymentService;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.executable.ValidateOnExecution;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int count = paymentService.create(payment);
        log.info("成功插入{}条记录",count);
        return CommonResult.success("条数:"+count+",端口:"+serverPort);
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment != null){
            log.info("查询成功");
        }else{
            return new CommonResult(-1,"查询无结果!");
        }
        return new CommonResult(0,"查询成功,port="+serverPort,payment);
    }
}
