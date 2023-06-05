package com.yicai.mall.api;

import com.yicai.mall.api.param.YiCaiMallUserLoginParam;
import com.yicai.mall.common.Result;
import com.yicai.mall.service.impl.YiCaiMallUserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class YiCaiMallUserAPI {

    @Resource
    private YiCaiMallUserServiceImpl service;

    @PostMapping("/login")
    public ResponseEntity<Result> login(@RequestBody @Valid YiCaiMallUserLoginParam param) {
        return service.login(param.getUsername(), param.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody @Valid YiCaiMallUserLoginParam param) {
        return service.register(param.getUsername(), param.getPassword());
    }

    @GetMapping("/info")
    public ResponseEntity<Result> info(@RequestParam("id") String id) {
        return service.info(id);
    }

}
