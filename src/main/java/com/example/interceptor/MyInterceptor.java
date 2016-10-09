package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年4月15日 下午1:11:17 
 * 自定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	
	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//logger.info("afterCompletion");
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		//logger.info("postHandle");
	}

	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		//logger.info("请求url" + arg0.getRequestURI());
		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

}
