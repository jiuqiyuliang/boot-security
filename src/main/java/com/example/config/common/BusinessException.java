package com.example.config.common;
/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月6日 下午4:38:24 
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}
}
