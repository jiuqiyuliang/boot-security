package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.annotation.LogInfo;
import com.example.annotation.LogType;
import com.example.config.common.HttpCode;
import com.example.config.common.ResultData;
import com.example.dao.vo.SysUser;
import com.example.service.SysUserService;

/**
 * @author lilufeng
 * @date 创建时间：2016年5月11日 上午9:19:19
 */

@Controller
@RequestMapping(value = "sysUser")
public class SysUserController {

	private static Logger logger = LoggerFactory
			.getLogger(SysUserController.class);

	@Autowired
	SysUserService sysUserService;

	@LogInfo(logType = LogType.修改, operationContent = "账户信息修改")
	@RequestMapping(value = "updateSysUser")
	@ResponseBody
	public ResultData updateSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.updateSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息修改成功");
		return resultData;
	}

	@LogInfo(logType = LogType.锁定, operationContent = "账户信息锁定")
	@RequestMapping(value = "lockSysUser")
	@ResponseBody
	public ResultData lockSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.lockSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息锁定成功");
		return resultData;
	}

	@LogInfo(logType = LogType.解除锁定, operationContent = "账户信息解除锁定")
	@RequestMapping(value = "unLockSysUser")
	@ResponseBody
	public ResultData unLockSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.unLockSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息解除锁定成功");
		return resultData;
	}

	@LogInfo(logType = LogType.新增, operationContent = "账户信息添加")
	@RequestMapping(value = "addSysUser")
	@ResponseBody
	public ResultData addSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.addSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息添加成功");
		return resultData;
	}
}
