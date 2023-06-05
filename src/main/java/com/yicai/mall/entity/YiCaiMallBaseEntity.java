package com.yicai.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class YiCaiMallBaseEntity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;

    // 物理删除：直接从数据库删除数据
    // 逻辑删除：更新字段，0表示未删除，1表示已删除
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
