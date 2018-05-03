package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

public class Signal extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public Signal() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signl.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		UserDAO userdao=new UserDAO();
		try {
			if(userdao.ifExist(username)){
				request.setAttribute("error", "用户名已被使用！");
				request.getRequestDispatcher("/signal.jsp").forward(request, response);
			}
			else{
				userdao.addUser(username,password);
				request.setAttribute("tishi", "您现在可以已用注册的账号登录啦！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
