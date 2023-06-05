package com.yicai.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yicai.mall.mapper")
public class YiCaiMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiCaiMallApplication.class, args);
    }

}
