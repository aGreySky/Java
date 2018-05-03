package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    private FilterConfig config;
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();// 创建session对象
        String noLoginPaths = config.getInitParameter("noLoginPaths");// 无需过滤的目录

        if (noLoginPaths != null) {
            String[] strArray = noLoginPaths.split(";");
            for (int i = 0; i < strArray.length; i++) {

                if (strArray[i] == null || "".equals(strArray[i])) {
                    continue;
                }

                if (request.getRequestURI().indexOf(strArray[i]) != -1) {
                    arg2.doFilter(arg0, arg1);
                    return;
                }
            }
        }

        if (session.getAttribute("user") != null) {// 如果session有user
            arg2.doFilter(arg0, arg1);// 通过
        } else {
            response.sendRedirect("index.html");// “过滤”--返回index.html
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        config = arg0;
    }

}
