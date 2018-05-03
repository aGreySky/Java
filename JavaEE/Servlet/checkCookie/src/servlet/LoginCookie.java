package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.StudentDao;
import entity.Student;


@WebServlet("/LoginCookie")
public class LoginCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginCookie() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("执行servlet");
		Student stu=new Student();
		stu.setStuname(request.getParameter("stuname"));
		stu.setStupwd(request.getParameter("stupwd"));
		StudentDao dao=new StudentDao();
		try {
			if(dao.login(stu)){ 
				Cookie cookie1=new Cookie("stuname", stu.getStuname());
				Cookie cookie2=new Cookie("stupwd",stu.getStupwd());
				cookie1.setMaxAge(30*24*60*60);
				cookie2.setMaxAge(30*24*60*60);
				response.addCookie(cookie1);
				response.addCookie(cookie2);
				System.out.println("写入Cookie");
				response.sendRedirect("loginSuccessCookie.jsp");
			}else{
				response.sendRedirect("loginFormCookie.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
