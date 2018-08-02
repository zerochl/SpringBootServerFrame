package com.zero.barrageserver.common.filter;

import com.zero.barrageserver.common.interceptormanager.advice.BarrageRequestParamWrapper;
import com.zero.barrageserver.common.interceptormanager.advice.BarrageRequestParamWrapper;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zero大神 on 2017/11/28.
 */
@Order(1)
//重点
@WebFilter(filterName = "requestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Map<String,String[]> m = new HashMap<String,String[]>(servletRequest.getParameterMap());
        servletRequest = new BarrageRequestParamWrapper((HttpServletRequest)servletRequest, m);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
