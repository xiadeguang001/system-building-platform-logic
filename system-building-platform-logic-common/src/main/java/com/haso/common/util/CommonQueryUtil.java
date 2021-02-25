package com.haso.common.util;

import javax.servlet.http.HttpServletRequest;

import com.haso.common.api.vo.BaseView;

public class CommonQueryUtil {
	
	public static Integer logic_flg_normal = 0;

	public static void updateOrderColumn(HttpServletRequest req, BaseView view) {
		String column = null;
		String order = null;
		if (req.getParameter("column")!=null && req.getParameter("order")!=null){
			//获取排序列名
			column = ConvertUtil.camelToUnderline(req.getParameter("column")) ;
			//获取排序方式
			order = req.getParameter("order");
		}
		view.setSortColumn(column);
		view.setSortColumnOrder(order);
	}
}
