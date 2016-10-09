package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.config.common.BusinessException;
import com.example.dao.SysUserMapper;
import com.example.dao.vo.SysUser;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月11日 上午9:18:34 
 */

@Service
public class SysUserService {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	@Autowired
	SysUserMapper sysUserMapper;

	public SysUser selectByAccountAndPassword(SysUser sysUser) {
		return sysUserMapper.selectByAccountAndPassword(sysUser);
	}

	@Transactional
	public boolean updateSysUser(SysUser sysUser) {
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息修改失败！");
		}
		return true;
	}

	/**
	 * 锁定用户信息（锁定） 锁定标志(1:锁定;0:激活)
	 * @param sysUser
	 * @return
	 */
	@Transactional
	public boolean lockSysUser(SysUser sysUser) {
		sysUser.setLocked("1");
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息锁定失败！");
		}
		return true;
	}

	@Transactional
	public boolean addSysUser(SysUser sysUser) {
		if(sysUserMapper.insertSelective(sysUser) < 1){
			throw new BusinessException("账户信息添加失败！");
		}
		return true;
	}

	/**
	 * 解除锁定用户信息    锁定标志(1:锁定;0:激活)
	 * @param sysUser
	 * @return
	 */
	@Transactional
	public boolean unLockSysUser(SysUser sysUser) {
		sysUser.setLocked("0");
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息解除锁定失败！");
		}
		return true;
	}
	@Transactional
	public int lockSysUserByAccount(SysUser sysUser) {
		sysUser.setLocked("1");
		return sysUserMapper.updateByAccountSelective(sysUser);
	}
}
