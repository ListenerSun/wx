package com.sqt.edu.core.filter;

import com.sqt.edu.core.constant.AuthConstants;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-01-19 11:07
 */
@Order(Integer.MIN_VALUE)
public class CorsFilter implements Filter {

    private static final String headers = "User-Agent,shareId,Origin,Cache-Control," +
            "Content-type,Date,Server,withCredentials," + AuthConstants.HTTP_HEADER_TOKEN + AuthConstants.HTTP_HEADER_USERID;

    @Override
    public void init(FilterConfig filterConfig) {
        // default implementation ignored
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", headers);
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Expires", "-1");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setHeader("pragma", "no-cache");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (!httpServletRequest.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, httpServletResponse);
        }
    }

    @Override
    public void destroy() {
        // default implementation ignored
    }
}
