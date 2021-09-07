package com.buptcpr.demo.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果集
 * @param <T>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String massage;
    private T data;

    public CommonResult(Integer code,String massage){

        this(code,massage,null);

    }
}


