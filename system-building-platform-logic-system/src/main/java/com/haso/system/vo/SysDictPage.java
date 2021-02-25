package com.haso.system.vo;

import com.haso.system.entity.SysDictItem;
import lombok.Data;

import java.util.List;

@Data
public class SysDictPage {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 删除状态
     */
    private Integer delFlag;
    /**
     * 描述
     */
    private String description;

    private List<SysDictItem> sysDictItemList;

}
