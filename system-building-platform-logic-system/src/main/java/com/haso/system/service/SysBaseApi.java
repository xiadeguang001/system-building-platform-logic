package com.haso.system.service;

import com.haso.common.constant.CacheConstant;
import com.haso.common.system.vo.LoginUser;
import com.haso.common.util.ConvertUtil;
import com.haso.common.util.IPUtil;
import com.haso.common.util.SpringContextUtil;
import com.haso.system.entity.SysLog;
import com.haso.system.entity.SysUser;
import com.haso.system.mapper.SysLogMapper;
import com.haso.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysBaseApiImpl
 * @Description TODO
 * @Author haso
 * @Date 2020/02/29 15:43
 * @Version 1.0
 **/
@Slf4j
@Service
public class SysBaseApi {
    @Resource
    private SysLogMapper sysLogMapper;
    @Resource
    private SysUserMapper userMapper;
//    @Autowired
//    private CommonMapper commonMapper;
    public void addLog(String LogContent, Integer logType, Integer operatetype) {
        SysLog sysLog = new SysLog();
        //注解上的描述,操作日志内容
        sysLog.setLogContent(LogContent);
        sysLog.setLogType(logType);
        sysLog.setOperateType(operatetype);

        //请求的方法名
        //请求的参数

        try {
            //获取request
            HttpServletRequest request = SpringContextUtil.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(IPUtil.getIpAddr(request));
        } catch (Exception e) {
            sysLog.setIp("127.0.0.1");
        }

        //获取登录用户信息
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if(sysUser!=null){
            sysLog.setUserid(sysUser.getId());
            sysLog.setUsername(sysUser.getRealname());

        }
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogMapper.insert(sysLog);
    }


    @Cacheable(cacheNames= CacheConstant.SYS_USERS_CACHE, key="#username")
    public LoginUser getUserByName(String username) {
        if(ConvertUtil.isEmpty(username)) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        SysUser sysUser = userMapper.getUserByName(username);
        if(sysUser==null) {
            return null;
        }
        BeanUtils.copyProperties(sysUser, loginUser);
        return loginUser;
    }
    public LoginUser getUserById(String id) {
        return null;
    }

    public List<String> getRolesByUsername(String username) {
        return null;
    }

    public List<String> getDepartIdsByUsername(String username) {
        return null;
    }

    public List<String> getDepartNamesByUsername(String username) {
        return null;
    }

    public String getDatabaseType() throws SQLException {
        return null;
    }

    public void sendSysAnnouncement(String fromUser, String toUser, String title, String msgContent) {

    }

    public void sendSysAnnouncement(String fromUser, String toUser, String title, Map<String, String> map, String templateCode) {

    }

    public String parseTemplateByCode(String templateCode, Map<String, String> map) {
        return null;
    }

    public void sendSysAnnouncement(String fromUser, String toUser, String title, String msgContent, String setMsgCategory) {

    }

    public List<String> getRoleIdsByUsername(String username) {
        return null;
    }

    public String getDepartIdsByOrgCode(String orgCode) {
        return null;
    }

    public String upload(MultipartFile file, String bizPath, String uploadType) {
        String url = "";
//        if(CommonConstant.UPLOAD_TYPE_MINIO.equals(uploadType)){
//            url = MinioUtil.upload(file,bizPath);
//        }else{
//            url = OssBootUtil.upload(file,bizPath);
//        }
        return url;
    }

    /**
     * 通过id查询实体类数据
     * @param id 数据库表字段ID
     * @param entityName 数据库表名
     * @param fields 需要查询数据的字段
     * @return List<Map<String, Object>> Map类数据
     */
    public List<Map<String, Object>> getEntityById(int id, String entityName,String fields) {

        char[] c=entityName.toCharArray();
        // 数据库名字和实体名字不同时候特殊处理
        String newTable = entityName;
        int isflg = 0;
        for(int i=0;i<entityName.length();i++){
            if(c[i]>='A'&&c[i]<='Z' && i != 0){
                newTable =  newTable.substring(0,(i+isflg)) + "_" + newTable.substring(i+isflg);
                isflg ++;
            }
        }
        // 检索出数据
//        List<Map<String, Object>> maps =
//                commonMapper.get(id, newTable, fields);
//        if (maps != null && !maps.isEmpty()) {
//            return maps;
//        }
        return null;
    }

}
