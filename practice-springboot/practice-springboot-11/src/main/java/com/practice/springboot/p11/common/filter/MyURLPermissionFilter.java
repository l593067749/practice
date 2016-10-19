package com.practice.springboot.p11.common.filter;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyURLPermissionFilter extends PermissionsAuthorizationFilter {

    private Logger log = Logger.getLogger(MyURLPermissionFilter.class);

    /*protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        String path = WebUtils.getRequestUri(req);
        if (path.startsWith("/my/")) {
            res.sendError(401);
            return false;
        } else {
            PrintWriter out = response.getWriter();
            out.println("{\"error_info\":\"permission denied.\"}");
            out.flush();
            out.close();
            return false;
        }
    }*/

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
        HttpServletRequest req = WebUtils.toHttp(request);
        String path = WebUtils.getRequestUri(req);
        String method = req.getMethod();
        String permission = path + ":" + method;
        //这里要交由其他的拦截器去处理，否则进不了你定义的ShiroRealm
       return super.isAccessAllowed(request,response,new String[]{permission});
    }
}