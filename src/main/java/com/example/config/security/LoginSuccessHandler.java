package com.example.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.annotation.LogType;
import com.example.dao.vo.SysLog;
import com.example.dao.vo.SysUser;
import com.example.service.SysLogService;
import com.example.util.IpUtil;

/**
 * 
 * @author lilufeng
 * 登录成功处理
 */

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private static Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Autowired
	SysLogService sysLogService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0,
			HttpServletResponse arg1, Authentication arg2) throws IOException,
			ServletException {
		SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("登录成功");
		SysLog sysLog = new SysLog();
		sysLog.setContent("登录成功");
		sysLog.setTypeKey(LogType.登录.value());
		sysLog.setTypeValue(LogType.登录.name());
		sysLog.setIp(IpUtil.getIpAddress(arg0));
		sysLog.setParams("登录帐号：" + sysUser.getAccount());
		sysLog.setAccount(sysUser.getAccount());
		sysLog.setResult("1");
		sysLog.setOperator(sysUser.getId());
		sysLogService.addSysLog(sysLog);
		arg1.sendRedirect(arg0.getContextPath() + "/mainJsp");
	}
}
