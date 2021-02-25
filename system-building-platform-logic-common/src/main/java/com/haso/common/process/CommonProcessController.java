package com.haso.common.process;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.haso.common.api.vo.Result;
import com.haso.common.util.ConvertUtil;

@RestController
@RequestMapping("/sys/commonProcess")
public class CommonProcessController {

	@RequestMapping("/process")
	public Result<?> process(@RequestBody CommonProcessRequest request) {
		Result<?> result = new Result<>();
		intercept(request);
		result = CommonProcessHelper.process(request);
		return result;
	}
	
	private void intercept(CommonProcessRequest request) {
		List<String> inteceptors = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(request.getInteceptors())) {
			inteceptors.addAll(request.getInteceptors());
		}
		if (!StringUtils.isEmpty(request.getDbDomain()) && request.getOperationMod() != null) {
			String defaultInterceptorName = StringUtils.underlineToCamel(request.getDbDomain()) + StringUtils.capitalize(request.getOperationMod().toString().toLowerCase()) + "Interceptor";
			inteceptors.add(defaultInterceptorName);
		}
		if (!CollectionUtils.isEmpty(inteceptors)) {
			for (String name : inteceptors) {
				CommonProcessHelper.intercept(name, request);
			}
		}
	}
	
	@RequestMapping("/simpleSave")
	public Result<?> simpleInsert(@RequestBody CommonProcessRequest request) {
		request.setOperationMod(ProcessType.SAVE);
		return process(request);
	}
	
	@RequestMapping("/simpleDelete")
	public Result<?> simpleDelete(@RequestBody CommonProcessRequest request) {
		request.setOperationMod(ProcessType.DELETE);
		return process(request);
	}
	
	@RequestMapping("/sqlProcess")
	public Result<?> sqlProcess(@RequestBody CommonProcessRequest request) {
		request.setOperationMod(ProcessType.SQL);
		return process(request);
	}
	
	@GetMapping("/page")
	public Result<?> page(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
			   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> paramaterNames = req.getParameterNames();
		while(paramaterNames.hasMoreElements()) {
			String name = paramaterNames.nextElement();
			paramMap.put(name, req.getParameter(name));
		}
		Result<?> result = new Result<>();
		req.getParameterNames();
		setOrderColumn(req, paramMap);
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		CommonProcessRequest request = new CommonProcessRequest();
		request.setDbDomain((String)paramMap.get("dbDomain"));
		request.setSqlId((String)paramMap.get("sqlId"));
		request.setOperationMod(ProcessType.PAGE_SELECT);
		List<Map<String, Object>> params = new ArrayList<>();
		params.add(paramMap);
		request.setParams(params);
		result = CommonProcessHelper.process(request);
		return result;
	}
	
	private void setOrderColumn(HttpServletRequest req, Map<String, Object> obj) {
		String column = null;
		String order = null;
		if (req.getParameter("column")!=null && req.getParameter("order")!=null){
			//获取排序列名
			column = ConvertUtil.camelToUnderline(req.getParameter("column")) ;
			//获取排序方式
			order = req.getParameter("order");
		}
		obj.put("sortColumn", column);
		obj.put("sortColumnOrder", order);
	}
}
