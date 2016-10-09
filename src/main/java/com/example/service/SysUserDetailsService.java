package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.dao.SysUserMapper;
import com.example.dao.vo.SysUser;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月13日 下午1:57:24 
 */
@Component
public class SysUserDetailsService implements UserDetailsService {

	@Autowired
	SysUserMapper sysUserMapper;
	
	@Override
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException {
		SysUser sysUser = new SysUser();
		sysUser.setAccount(account);
		sysUser = sysUserMapper.selectByAccountAndPassword(sysUser);
		if(null == sysUser){
			throw new UsernameNotFoundException("用户不存在！");
		}
		return sysUser;
	}

}
