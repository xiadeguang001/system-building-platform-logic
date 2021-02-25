package com.haso.common.exception;

import com.haso.common.api.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(AppException.class)
    public Result<?> handleRRException(AppException e){
//        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handlerNoFoundException(Exception e) {
//        log.error(e.getMessage(), e);
        return Result.error(404, "パスが存在しません。パスが正しいかどうか確認してください。");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
//        log.error(e.getMessage(), e);
        return Result.error("このレコードはデータベースに既に存在します。");
    }

    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Result<?> handleAuthorizationException(AuthorizationException e){
//        log.error(e.getMessage(), e);
        return Result.noauth("権限がありません。管理者に連絡して許可してください。");
    }

    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e){
//        log.error(e.getMessage(), e);
        return Result.error("操作失敗，"+e.getMessage());
    }

    /**
     * @Author 政辉
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        StringBuffer sb = new StringBuffer();
        sb.append("不支持");
        sb.append(e.getMethod());
        sb.append("请求方法，");
        sb.append("支持以下");
        String [] methods = e.getSupportedMethods();
        if(methods!=null){
            for(String str:methods){
                sb.append(str);
                sb.append("、");
            }
        }
//        log.error(sb.toString(), e);
        //return Result.error("没有权限，请联系管理员授权");
        return Result.error(405,sb.toString());
    }

    /**
     * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
//        log.error(e.getMessage(), e);
        return Result.error("ファイルサイズが10 MBの制限を超えています。ファイルサイズを圧縮するか削減してください! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        log.error(e.getMessage(), e);
        return Result.error("フィールドが長すぎ、DBの長さを超えています");
    }

//    @ExceptionHandler(PoolException.class)
//    public Result<?> handlePoolException(PoolException e) {
////        log.error(e.getMessage(), e);
//        return Result.error("Redis 连接异常!");
//    }

    @ExceptionHandler(HandlerExcelcntException.class)
    public Result<?> handlerExcelcntException(Exception e) {
//        log.error(e.getMessage(), e);
        return Result.error(500, "データが変更されました。一覧画面を更新して、もう一度変更してください!");
    }
}
