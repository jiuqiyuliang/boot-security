package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.util.MHttpServletRequest;

/** 
 * @author  lilufeng 
 * @date 创建时间：2016年4月21日 下午5:36:35 
 */
public class IllegalCharacterFilter implements Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        //傳入參數特殊字符過濾
        request = new MHttpServletRequest(request);
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {

    }

}
