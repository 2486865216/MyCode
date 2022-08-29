package com.example.filtertest;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*value = "/test/*"
* value = "/test1.html"
* value = "*.html"
* */
@WebFilter(filterName = "FilterTest01", value = "/test/*")
public class FilterTest01 implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println(config.getFilterName());
        System.out.println(config.getInitParameter("user"));
        System.out.println(config.getInitParameterNames());
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter");
        chain.doFilter(request, response);
    }
}
