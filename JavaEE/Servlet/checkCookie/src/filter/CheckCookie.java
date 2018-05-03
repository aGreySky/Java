package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import entity.Student;


@WebFilter("/loginFilter")
public class CheckCookie implements Filter {

    public CheckCookie() {  
    }

	public void destroy() {	
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length>0){
			Student stu=new Student();
			for(Cookie c:cookies){
				if(c.getName().equals("stuname"))
					stu.setStuname(c.getValue());
				else if(c.getName().equals("stupwd"))
					stu.setStupwd(c.getValue());
			}
			if(stu.getStuname()!=null&&stu.getStupwd()!=null){
				StudentDao dao=new StudentDao();
				try {
					if(dao.login(stu)){ //如果数据库中存在该用户，则登录成功
						response.sendRedirect("loginSuccessCookie.jsp");
					}else{
						response.sendRedirect("loginFormCookie.jsp");//返回登录界面
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("没执行过滤");
				chain.doFilter(req, res);
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
