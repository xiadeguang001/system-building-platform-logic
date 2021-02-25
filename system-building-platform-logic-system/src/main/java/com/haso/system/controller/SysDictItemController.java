package com.haso.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haso.common.api.vo.Result;
import com.haso.common.constant.CacheConstant;
import com.haso.common.constant.MessageConstant;
import com.haso.common.system.QueryGenerator;
import com.haso.common.util.ConvertUtil;
import com.haso.common.util.MessageUtil;
import com.haso.system.entity.SysDictItem;
import com.haso.system.service.SysDictItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * 系统管理：字典列表 前端控制器。
 *
 * <pre>
 *
 * Ver.   变更/障害No.    修改日期      作成/变更者       修改内容
 * ------ ------------- ------------ --------------- -----------------------------
 * 1.0    #13914        2020-05-10   Fengbo.Ge       初期作成
 * </pre>
 */
@RestController
@RequestMapping("/sys/dictItem")
@Slf4j
public class SysDictItemController {

	/**
	 * 字典列表（字典配置）
	 */
	@Autowired
	private SysDictItemService sysDictItemService;

	/**
	 * @功能：查询字典数据
	 * @param sysDictItem 字典表对象
	 * @param pageNo 当前页数
	 * @param pageSize 一页显示件数
	 * @param req Http请求
	 * @return 处理结果
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result<IPage<SysDictItem>> queryPageList(SysDictItem sysDictItem, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
													@RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req) {
		Result<IPage<SysDictItem>> result = new Result<IPage<SysDictItem>>();
		QueryWrapper<SysDictItem> queryWrapper = QueryGenerator.initQueryWrapper(sysDictItem, req.getParameterMap());
		queryWrapper.orderByAsc("sort_order");
		Page<SysDictItem> page = new Page<SysDictItem>(pageNo, pageSize);
		// 调用共同查询一览数据
		IPage<SysDictItem> pageList = sysDictItemService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * @功能：新增
	 *
	 * @param sysDictItem 字典表对象
	 * @return 处理结果
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@CacheEvict(value= CacheConstant.SYS_DICT_CACHE, allEntries=true)
	public Result<SysDictItem> add(@RequestBody SysDictItem sysDictItem) {
		Result<SysDictItem> result = new Result<SysDictItem>();
		try {
			sysDictItem.setCreateTime(new Date());
			// 保存数据
			sysDictItemService.save(sysDictItem);
			// 保存成功
			result.success(MessageUtil.getMsg(MessageConstant.SAVE_SUCCESS));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			// 操作失败
			result.error500(MessageUtil.getMsg(MessageConstant.SAVE_FAIL));
		}
		return result;
	}

	/**
	 * @功能：编辑
	 *
	 * @param sysDictItem 字典表对象
	 * @return 处理结果
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@CacheEvict(value=CacheConstant.SYS_DICT_CACHE, allEntries=true)
	public Result<SysDictItem> edit(@RequestBody SysDictItem sysDictItem) {
		Result<SysDictItem> result = new Result<SysDictItem>();
		// 字典存在check
		SysDictItem sysdict = sysDictItemService.getById(sysDictItem.getId());
		if(sysdict==null) {
			// 此{0}数据不存在
			result.error500(MessageUtil.getMsg(MessageConstant.DATA_NOT_FIND, MessageUtil.getLab("dict")));
		}else {
			try {
				sysDictItem.setUpdateTime(new Date());
				// 根据画面修改表数据
				boolean ok = sysDictItemService.updateById(sysDictItem);
				//TODO 返回false说明什么？
				if(ok) {
					// 编辑成功
	//				result.success(MessageUtils.getMsg(MessageConstant.EDIT_SUCCESS));
					result.success("修改成功!");
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				result.error500("操作失败");
//				result.error500(MessageUtils.getMsg(MessageConstant.SAVE_FAIL));
			}
		}
		return result;
	}

	/**
	 * @功能：删除字典数据
	 * @param id 一览中选择的删除行的id
	 * @return 处理结果
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@CacheEvict(value=CacheConstant.SYS_DICT_CACHE, allEntries=true)
	public Result<SysDictItem> delete(@RequestParam(name="id",required=true) String id) {
		Result<SysDictItem> result = new Result<SysDictItem>();
		SysDictItem joinSystem = sysDictItemService.getById(ConvertUtil.getInteger(id));
		if(joinSystem==null) {
			// 此{0}数据不存在
			result.error500(MessageUtil.getMsg(MessageConstant.DATA_NOT_FIND, MessageUtil.getLab("dict")));
		}else {
			// 修改字典表的 delFLG为1
			boolean ok = sysDictItemService.removeById(ConvertUtil.getInteger(id));
			if(ok) {
				// 删除成功
				result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
			}
		}
		return result;
	}

	/**
	 * @功能：批量删除字典数据
	 * @param ids 一览中选择的删除行的id
	 * @return 处理结果
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
	@CacheEvict(value=CacheConstant.SYS_DICT_CACHE, allEntries=true)
	public Result<SysDictItem> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<SysDictItem> result = new Result<SysDictItem>();
		if(ids==null || "".equals(ids.trim())) {
			// 参数不识别
			result.error500(MessageUtil.getMsg(MessageConstant.PARAMETER_NOT_INCORRECT));
		}else {
			// 修改字典表的 delFLG为1
			this.sysDictItemService.removeByIds(Arrays.asList(ids.split(",")));
			// 删除成功
			result.success(MessageUtil.getMsg(MessageConstant.DELETE_SUCCESS));
		}
		return result;
	}

}
