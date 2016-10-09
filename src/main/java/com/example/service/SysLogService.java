package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SysLogMapper;
import com.example.dao.vo.SysLog;
/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月11日 下午1:31:43 
 */
@Service
public class SysLogService {

	@Autowired
	SysLogMapper sysLogMapper;

	public boolean addSysLog(SysLog sysLog) {
		return sysLogMapper.insertSelective(sysLog) < 1 ? false : true;
	}

	public List<SysLog> selectSysLogByAccountAndDate(String account) {
		return sysLogMapper.selectSysLogByAccountAndDate(account);
	}
	
}
