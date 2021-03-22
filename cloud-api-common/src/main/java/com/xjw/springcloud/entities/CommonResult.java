package com.xjw.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }


    public static <T>CommonResult success(T t){
        return new CommonResult(0,"操作成功",t);
    }

    public static CommonResult fail(String message){
        return new CommonResult(-1,"操作失败",message);
    }
}
