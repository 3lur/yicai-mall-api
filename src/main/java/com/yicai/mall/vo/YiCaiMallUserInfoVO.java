package com.yicai.mall.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class YiCaiMallUserInfoVO implements Serializable {
    private Long id;

    @TableField("username")
    private String username;

    @TableField("rule_type")
    private Integer ruleType;

    @TableField("balance")
    private Double balance;
}
