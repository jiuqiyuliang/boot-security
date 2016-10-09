package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年4月27日 下午7:47:31 
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
public @interface LogInfo {
	/**
	 * 日志类型
	 * @return
	 */
	LogType logType();
	/**
	 * 描述
	 * @return
	 */
	String operationContent();
}
