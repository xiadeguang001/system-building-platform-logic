package com.haso.system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.haso.common.constant.CommonConstant;
import com.haso.common.exception.HandlerExcelcntException;
import com.haso.common.system.vo.LoginUser;
import com.haso.common.util.ConvertUtil;
import com.haso.system.service.SysBaseApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.haso.common.util.OConvertUtil.object2Map;

/**
 * 处理新增和更新的基础数据填充，配合BaseEntity和MyBatisPlusConfig使用
 */
@Component
@Slf4j
public class MetaHandler implements MetaObjectHandler {

    @Autowired
    @Lazy
    private SysBaseApi sysBaseAPI;
    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser userEntity = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (userEntity != null) {
            this.setFieldValByName("createTime", new Timestamp(System.currentTimeMillis()), metaObject);
            if (userEntity.getId() != null)
                this.setFieldValByName("createUserId", userEntity.getId(), metaObject);

            this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
            if (userEntity.getId() != null)
                this.setFieldValByName("updateUserId", userEntity.getId(), metaObject);
        }
    }

    /**
     * 更新数据执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) throws HandlerExcelcntException {
        LoginUser userEntity = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (userEntity != null) {
            this.setFieldValByName("updateTime", new Timestamp(System.currentTimeMillis()), metaObject);
            if (userEntity.getId() != null)
                this.setFieldValByName("updateUserId", userEntity.getId(), metaObject);
        }

        // 对排他字段自动 +1 （页面需要埋入 exclcnt 字段）
        Object ob = metaObject.getOriginalObject();
        Map<String, String> map = (Map<String, String>) ob;
        Object obParams = map.get(CommonConstant.COMMON_PARAM1);
        Map<String, Object> mapParam = object2Map(obParams);
        if (mapParam.containsKey(CommonConstant.COMMON_EXCLCNT)) {
            int exclcnt = 0;
            if (!ConvertUtil.isEmpty(mapParam.get(CommonConstant.COMMON_EXCLCNT))) {
                exclcnt = ConvertUtil.getInteger(ConvertUtil.getString(mapParam.get(CommonConstant.COMMON_EXCLCNT)));
            }
            int id = ConvertUtil.getInteger(ConvertUtil.getString(mapParam.get("id")));
            Class clasz = obParams.getClass();
            // 根据id,table名字可以检索出需要的字段数据
            List<Map<String, Object>> list = sysBaseAPI.getEntityById(id,clasz.getSimpleName(),CommonConstant.COMMON_EXCLCNT);

            // 数据库排他和页面排他不同
            if(!ConvertUtil.isEmpty(list)){
                String exclcntDataBase = ConvertUtil.getString(list.get(0).get(CommonConstant.COMMON_EXCLCNT));
                if(!ConvertUtil.isEmpty(exclcntDataBase)){
                    int exclcntFinal = ConvertUtil.getInteger(exclcntDataBase);
                    if(exclcntFinal != exclcnt){
                        // 数据库排他和页面排他不同,报错
                        throw new HandlerExcelcntException("数据库排他!");
                    }
                }
            }

            this.setFieldValByName(CommonConstant.COMMON_EXCLCNT, exclcnt + 1, metaObject);
        }
    }

}
