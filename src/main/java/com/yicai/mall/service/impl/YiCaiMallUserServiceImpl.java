package com.yicai.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yicai.mall.common.Result;
import com.yicai.mall.entity.YiCaiMallUserEntity;
import com.yicai.mall.mapper.YiCaiMallUserMapper;
import com.yicai.mall.service.YiCaiMallUserService;
import com.yicai.mall.util.JWTUtil;
import com.yicai.mall.vo.YiCaiMallUserInfoVO;
import com.yicai.mall.vo.YiCaiMallUserTokenVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/*
 * YiCaiMallUserServiceImpl
 *   + 不需要手动注解 Mapper 类，mybatis plus 内部实现了
 *   + 必须继承 mybatis 提供的 ServiceImpl 类
 * */
@Service
public class YiCaiMallUserServiceImpl extends ServiceImpl<YiCaiMallUserMapper, YiCaiMallUserEntity> implements YiCaiMallUserService {

    @Override
    public ResponseEntity<Result> login(String username, String password) {
        // 根据用户名从数据库中查找，用户名不可能会重复，所以不存在找出多条数据
        LambdaQueryWrapper<YiCaiMallUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YiCaiMallUserEntity::getUsername, username);
        YiCaiMallUserEntity user = baseMapper.selectOne(wrapper);

        // 如果找到了，判断密码是否正确
        if (user != null) {
            // TODO 密码要 MD5 解密
            String passwordDb = user.getPassword();
            // 密码错误
            if (!passwordDb.equals(password)) {
                return Result.error(401, "账号或密码错误");
            }
            // 根据用户名和用户ID创建JWT
            String jwt = JWTUtil.createJWT(user.getId(), user.getUsername());
            YiCaiMallUserTokenVO vo = new YiCaiMallUserTokenVO();
            vo.setToken(jwt);
            return Result.ok(vo);
        }
        // 为了安全起见，没有直接提示用户不存在
        return Result.error(401, "账号或密码错误");
    }

    @Override
    public ResponseEntity<Result> register(String username, String password) {
        // 1. 判断用户名是否存在
        LambdaQueryWrapper<YiCaiMallUserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(YiCaiMallUserEntity::getUsername, username);
        YiCaiMallUserEntity user = baseMapper.selectOne(wrapper);
        if (user != null) {
            return Result.error(409, "用户已注册");
        }

        // 2. 如果不存在，进行注册
        YiCaiMallUserEntity entity = new YiCaiMallUserEntity();
        entity.setUsername(username);
        // TODO MD5 密码加密
        entity.setPassword(password);
        baseMapper.insert(entity);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", entity.getUsername());
        return Result.ok(map);
    }

    @Override
    public ResponseEntity<Result> info(String userID) {
        YiCaiMallUserEntity user = baseMapper.selectById(userID);
        if (user == null) {
            return Result.error(404, "用户不存在");
        }
        YiCaiMallUserInfoVO vo = new YiCaiMallUserInfoVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setRuleType(user.getRuleType());
        vo.setBalance(user.getBalance());
        return Result.ok(vo);
    }
}
