package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.config.common.HttpCode;
import com.example.config.common.ResultData;
import com.example.config.common.SysParamet;
import com.example.service.SysUserService;

/**
 * @author lilufeng
 * @date 创建时间：2016年5月12日 下午4:20:59
 */

@Controller
public class AuthController {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	SysParamet sysParamet;

	@RequestMapping(value = "/loginJsp")
	public String loginJsp(String login_failure, String login_out,
			String overdue, ModelMap map) {
		if (null != login_failure) {
			if(Integer.valueOf(login_failure) == -1){
				map.put("msg", "账户不存在！");
			}else if(Integer.valueOf(login_failure) == -2){
				map.put("msg", "账户已被锁定！");
			}else if(Integer.valueOf(login_failure) == -3){
				map.put("msg", "账户已被禁用！");
			}else if(Integer.valueOf(login_failure) == -4){
				map.put("msg", "证书过期！");
			}else if(Integer.valueOf(login_failure) == -5){
				map.put("msg", "未知原因！");
			}else{
				if(Integer.valueOf(login_failure) <= sysParamet.getMaxAttempts()){
					int c = sysParamet.getMaxAttempts() - Integer.valueOf(login_failure);
					if(c != 0){
						map.put("msg", "用户名或密码不正确!您还可以登陆" + c + "次，当天" + sysParamet.getMaxAttempts() + "次登陆错误账户将被锁定！");
					}else{
						map.put("msg", "账户已被锁定，请明天登陆或联系管理员！");
					}
				}
			}
		}
		if (null != login_out) {
			map.put("msg", "您已成功注销退出系统.");
		}
		if (null != overdue) {
			map.put("msg", "登录过期,请重新登录.");
		}
		return "login";
	}

	@RequestMapping(value = "mainJsp")
	public String mainJsp() {
		return "main";
	}

	@RequestMapping(value = "403")
	public String accessDeniedPage() {
		return "error/403";
	}

	/*
	 * @LogInfo(logType = LogType.登录 , operationContent = "登录系统")
	 * 
	 * @RequestMapping(value = "login")
	 * 
	 * @ResponseBody public ResultData login(SysUser sysUser) { ResultData
	 * resultData = new ResultData(); Assert.notNull(sysUser.getAccount(),
	 * "账户不能为空！"); Assert.notNull(sysUser.getPassword(), "密码不能为空！"); sysUser =
	 * sysUserService.selectByAccountAndPassword(sysUser);
	 * resultData.setHttpCode(HttpCode.OK.value());
	 * resultData.setMsg(HttpCode.OK.msg()); resultData.setResult(sysUser);
	 * return resultData; }
	 */

	/*
	 * @LogInfo(logType = LogType.退出 , operationContent = "退出系统")
	 * 
	 * @RequestMapping(value = "loginOut")
	 * 
	 * @ResponseBody public ResultData loginOut() { ResultData resultData = new
	 * ResultData(); SysUser sysUser = (SysUser)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * return resultData; }
	 */

	@RequestMapping(value = "getSystemTime")
	@ResponseBody
	public ResultData getSystemTime() {
		ResultData resultData = new ResultData();
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg(String.valueOf(System.currentTimeMillis()));
		return resultData;
	}
}
