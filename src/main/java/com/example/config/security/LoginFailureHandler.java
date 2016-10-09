package com.example.config.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.annotation.LogType;
import com.example.config.common.SysParamet;
import com.example.dao.vo.SysLog;
import com.example.dao.vo.SysUser;
import com.example.service.SysLogService;
import com.example.service.SysUserService;
import com.example.util.IpUtil;

/**
 * 
 * @author lilufeng
 * 登录失败处理
 */

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private static Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

	@Autowired
	SysLogService sysLogService;
	
	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	SysParamet sysParamet;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0,
			HttpServletResponse arg1, AuthenticationException e)
			throws IOException, ServletException {
		String account = arg0.getParameter("username").toString();
		logger.info("登录失败:" + e.getMessage());
		SysLog sysLog = new SysLog();
		sysLog.setContent("登录失败");
		sysLog.setFailureCause(e.getMessage());
		sysLog.setTypeKey(LogType.登录.value());
		sysLog.setTypeValue(LogType.登录.name());
		sysLog.setIp(IpUtil.getIpAddress(arg0));
		sysLog.setParams("登录帐号：" + account);
		sysLog.setAccount(account);
		sysLog.setResult("0");
		sysLogService.addSysLog(sysLog);
		
		//用户找不到
		if(e instanceof UsernameNotFoundException){
			logger.info("用户找不到");
		//坏的凭据
		}else if(e instanceof BadCredentialsException){
			logger.info("坏的凭据");
			SysUser sysUser = new SysUser();
			sysUser.setAccount(account);
			sysUser = sysUserService.selectByAccountAndPassword(sysUser);
			if(null != sysUser){
				List<SysLog> sysLogs = sysLogService.selectSysLogByAccountAndDate(account);
				if(null != sysLogs && sysLogs.size() >= sysParamet.getMaxAttempts()){
					SysUser sysUser1 = new SysUser();
					sysUser1.setAccount(account);
					if(sysUserService.lockSysUserByAccount(sysUser1) == 1){
						logger.info("账户:" + account + "登陆失败超过" + sysParamet.getMaxAttempts() + "次，此账户已锁定");
					}
				}
				int count = null == sysLogs ? 0 : sysLogs.size();
				arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=" + count);
			}else{
				arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=-1");
			}
		//账户锁定
		}else if(e instanceof LockedException){
			logger.info("账户锁定");
			arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=-2");
		//账户不可用
		}else if(e instanceof DisabledException){
			logger.info("账户不可用");
			arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=-3");
		//证书过期
		}else if(e instanceof CredentialsExpiredException){
			logger.info("证书过期");
			arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=-4");
		}else{
			logger.info("未知原因");
			arg1.sendRedirect(arg0.getContextPath() + "/loginJsp?login_failure=-5");
		}
	}
}
