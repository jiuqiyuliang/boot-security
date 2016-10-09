package com.example.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.service.SysUserDetailsService;


/** 
 * @author  lilufeng 
 * @date 创建时间：2016年5月13日 下午12:18:05 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean  
    public SysUserDetailsService getSysUserDetailsService() {  
		SysUserDetailsService userDetailsService = new SysUserDetailsService();  
        return userDetailsService;  
    }
	
	@Bean
	public LoginFailureHandler getLoginFailureHandler() {  
		LoginFailureHandler loginFailureHandler = new LoginFailureHandler();
        return loginFailureHandler;  
    }
	
	@Bean
	public LoginSuccessHandler getLoginSuccessHandler() {  
		LoginSuccessHandler loginSuccessHandler = new LoginSuccessHandler();
        return loginSuccessHandler;  
    }
	
	@Bean
	public LogOutHandler getLogOutHandler() {  
		LogOutHandler logOutHandler = new LogOutHandler();
        return logOutHandler;  
    }
	
	@Bean
	public SysSessionAuthenticationStrategy getSysSessionAuthenticationStrategy() {  
		SysSessionAuthenticationStrategy sysSessionAuthenticationStrategy = new SysSessionAuthenticationStrategy();
		return sysSessionAuthenticationStrategy;  
	}

	@Override  
    public void configure(WebSecurity web) throws Exception {  
        // 设置不拦截规则  
        web.ignoring().antMatchers("/css/**","/js/**","/library/**");
    } 
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		 http.
		 exceptionHandling()
		 	.accessDeniedPage("/403")
		 	.and()
         .authorizeRequests()
             .antMatchers("/loginJsp", "/getSystemTime").permitAll()
             .anyRequest().authenticated();
         http.csrf().disable().formLogin()
             .loginPage("/loginJsp")
             .usernameParameter("username")
             .passwordParameter("password")
             .loginProcessingUrl("/doLogin")
             .failureHandler(getLoginFailureHandler())
             .successHandler(getLoginSuccessHandler());
         http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
         http.logout().addLogoutHandler(getLogOutHandler())
	         .invalidateHttpSession(true)
	         .deleteCookies("remove")
	         .logoutUrl("/doLogout").logoutSuccessUrl("/loginJsp?login_out=1");
         http.sessionManagement()
         	 .maximumSessions(1)
         	 .maxSessionsPreventsLogin(true)
         	 .expiredUrl("/loginJsp?overdue=1");
    }
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义UserDetailsService  
        //auth.userDetailsService(sysUserDetailsService()).passwordEncoder(new Md5PasswordEncoder());  
		auth.userDetailsService(getSysUserDetailsService());
    }
}
