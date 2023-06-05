package com.yicai.mall.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@TableName("address")
public class YiCaiMallAddressEntity {
    private Long id;

    private Long userId;

    private String address;

    private Long phoneNumber;

    // 物理删除：直接从数据库删除数据
    // 逻辑删除：更新字段，0表示未删除，1表示已删除
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
}
