package com.yicai.mall.common;


import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(200, "SUCCESS"),
    FAIL(201, "ERROR");

    private Integer code;
    private String message;

    private ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}