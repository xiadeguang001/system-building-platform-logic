package com.haso.system.module.demo.controller;

import com.haso.common.api.vo.Result;
import com.haso.system.module.demo.service.ITestAsyncService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequestMapping("/demo")
@Api(tags="Demo演示")
public class DemoController {
    @Autowired
    private ITestAsyncService testAsyncService;
    /**
     * @功能：查询异步任务
     * @param req
     * @return
     */
    @RequestMapping(value = "/getAsync", method = RequestMethod.GET)
    public Result<String> getAsync( HttpServletRequest req) {
        Result<String> result = new Result<String>();
        Future<String> fs =  testAsyncService.asyncTask("yaobin");
        String strResult ="";
        boolean isStop=false;
        while(!isStop){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            strResult =String.valueOf( fs.isDone());
            isStop=true;
        }
        result.setSuccess(true);
        result.setResult(strResult);
        return result;
    }

}
