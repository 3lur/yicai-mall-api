package com.yicai.mall;

import com.yicai.mall.entity.YiCaiMallUserEntity;
import com.yicai.mall.service.impl.YiCaiMallUserServiceImpl;
import com.yicai.mall.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YiCaiMallApplicationTests {

    @Autowired
    private YiCaiMallUserServiceImpl service;

    @Test
    void contextLoads() {

    }

    @Test
    public void getAll() {
        List<YiCaiMallUserEntity> list = service.list();
        System.out.println(list);
    }

    @Test
    public void add() {
        YiCaiMallUserEntity userEntity = new YiCaiMallUserEntity();
        userEntity.setUsername("3lur");
        userEntity.setPassword("12345678");
        userEntity.setBalance(10000.00);
    }

    @Test
    public void deleteID() {
    }

    @Test
    public void testJWT() {
        JWTUtil jwtUtil = new JWTUtil();
        boolean b = jwtUtil.verifyJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBVVRIX1VTRVIiLCJpc3MiOiJZaUNhaU1hbGwiLCJpYXQiOjE2ODU5MzY0NzYsImV4cCI6MTY4NTkzODYzNiwidXNlcklEIjoxLCJ1c2VyTmFtZSI6IkVyaWMifQ.YUo6wvr7dD3ZwHVAeGABBmqjjO7X_NhegJ5h8ywu3IY");
        System.out.println(b);

        String userName = jwtUtil.getValueFromJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBVVRIX1VTRVIiLCJpc3MiOiJZaUNhaU1hbGwiLCJpYXQiOjE2ODU5MzY0NzYsImV4cCI6MTY4NTkzODYzNiwidXNlcklEIjoxLCJ1c2VyTmFtZSI6IkVyaWMifQ.YUo6wvr7dD3ZwHVAeGABBmqjjO7X_NhegJ5h8ywu3IY", "userName");

        System.out.println(userName);

    }
}
