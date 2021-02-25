package com.haso.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haso.system.entity.SysPosition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

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
@Mapper
public interface SysPositionMapper extends BaseMapper<SysPosition> {


    /**
     * 职位管理 编辑的时候check
     * @param tableName 表名
     * @param fieldName 字段名
     * @param newFieldVal 字段值
     * @param oldFieldVal 字段值
     * @return 处理结果
     */
    Long editDuplicateCheck(@Param("tableName")String tableName, @Param("fieldName")String fieldName, @Param("fieldVal")String newFieldVal, @Param("oldFieldVal")String oldFieldVal );

    /**
     * 职位管理 新建的时候check
     * @param tableName 表名
     * @param fieldName 字段名
     * @param fieldVal 字段值
     * @return 处理结果
     */
    Long addDuplicateCheck(@Param("tableName")String tableName, @Param("fieldName")String fieldName, @Param("fieldVal")String fieldVal);


}
