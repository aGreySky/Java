package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;
@WebServlet("/signal")
public class Signal extends HttpServlet {

	
	
	private static final long serialVersionUID = 1L;


	public Signal() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/index.html").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setUseremail(request.getParameter("useremail"));
		user.setPhone(request.getParameter("phone"));
		UserDAO userdao=new UserDAO();
		String json=null;
		try {
			if(userdao.ifExist(user.getUsername())){
				json="{\"res\":\"用户名已存在，请重新输入...\",\"flag\":false}";
				System.out.println("注册失败");
			}else{
				userdao.addUser(user);
				request.getSession().setAttribute("user", user);
				json="{\"res\":\"注册成功...\",\"flag\":true}";
				System.out.println("注册成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out=response.getWriter();
		out.println(json);
	}

	
	public void init() throws ServletException {
	}

}
