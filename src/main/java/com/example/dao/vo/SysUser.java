package com.example.dao.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SysUser implements UserDetails {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4029962337144280673L;

	private Integer id;

    private String account;

    private String password;

    private String sex;

    private String userName;

    private String avatar;

    private String userType;

    private Integer deptId;

    private String locked;

    private String usable;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

    public String getUsable() {
        return usable;
    }

    public void setUsable(String usable) {
        this.usable = usable == null ? null : usable.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authos = new ArrayList<>();
	    SimpleGrantedAuthority sim = new SimpleGrantedAuthority("ROLE_USER");
	    authos.add(sim);
	    return authos;
	}

	@Override
	public String getUsername() {
		return account;
	}

	/**
	 * 判断帐号是否已经过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 判断帐号是否已被锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		//锁定标志(1:锁定;0:激活)
		return locked.equals("0") ? true : false;
	}

	/**
	 * 判断用户凭证是否已经过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 判断用户帐号是否已启用  
	 */
	@Override
	public boolean isEnabled() {
		return usable.equals("1") ? true : false;
	}
}