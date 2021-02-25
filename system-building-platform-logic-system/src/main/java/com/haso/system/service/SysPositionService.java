package com.haso.system.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haso.system.entity.SysPosition;
import com.haso.system.mapper.SysPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职位表对象
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914         2020-05-13    hejunnan         初期作成
 * </pre>
 */
@Service
public class SysPositionService extends ServiceImpl<SysPositionMapper, SysPosition> {
    /**
     * 职位表 mapper
     */
    @Autowired
    private SysPositionMapper sysPositionMapper;


    /**
     * 职位管理 编辑的时候check
     *
     * @param tableName 表名
     * @param fieldName 字段名
     * @param newFieldVal  字段值
     * @param oldFieldVal  字段值
     * @return 处理结果
     */
    @Transactional
    public Long editDuplicateCheck(String tableName, String fieldName, String newFieldVal, String oldFieldVal) {
        Long num = null;
        num = sysPositionMapper.editDuplicateCheck(tableName, fieldName, newFieldVal,oldFieldVal);
        return num;
    }

    /**
     * 职位管理 新建的时候check
     *
     * @param tableName 表名
     * @param fieldName 字段名
     * @param fieldVal  字段值
     * @return 处理结果
     */
    @Transactional
    public Long addDuplicateCheck(String tableName, String fieldName, String fieldVal) {
        Long num = null;
        num = sysPositionMapper.addDuplicateCheck(tableName, fieldName, fieldVal);
        return num;
    }
}
