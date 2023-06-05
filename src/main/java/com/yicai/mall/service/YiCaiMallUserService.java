package com.yicai.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yicai.mall.common.Result;
import com.yicai.mall.entity.YiCaiMallUserEntity;
import org.springframework.http.ResponseEntity;

public interface YiCaiMallUserService extends IService<YiCaiMallUserEntity> {
    ResponseEntity<Result> login(String username, String password);

    ResponseEntity<Result> register(String username, String password);

    ResponseEntity<Result> info(String userID);
}
