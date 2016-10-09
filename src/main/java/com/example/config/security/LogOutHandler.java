package com.example.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.annotation.LogType;
import com.example.dao.vo.SysLog;
import com.example.dao.vo.SysUser;
import com.example.service.SysLogService;
import com.example.util.IpUtil;

/**
 *  退出系统前处理
 * @author lilufeng
 *
 */
public class LogOutHandler implements LogoutHandler {

	private static Logger logger = LoggerFactory.getLogger(LogOutHandler.class);
	
	@Autowired
	SysLogService sysLogService;
	
	@Override
	public void logout(HttpServletRequest arg0, HttpServletResponse arg1,
			Authentication arg2) {
		SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("注销退出。。。。");
		SysLog sysLog = new SysLog();
		sysLog.setContent("注销退出登录");
		sysLog.setTypeKey(LogType.退出.value());
		sysLog.setTypeValue(LogType.退出.name());
		sysLog.setIp(IpUtil.getIpAddress(arg0));
		sysLog.setParams("退出帐号：" + sysUser.getAccount());
		sysLog.setAccount(sysUser.getAccount());
		sysLog.setResult("1");
		sysLog.setOperator(sysUser.getId());
		sysLogService.addSysLog(sysLog);
	}
}
