package com.example.dao;

import com.example.dao.vo.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser selectByAccountAndPassword(SysUser sysUser);

	int updateByAccountSelective(SysUser sysUser);
}