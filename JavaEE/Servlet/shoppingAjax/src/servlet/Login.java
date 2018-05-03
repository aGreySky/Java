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
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Login() {
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
		UserDAO userdao=new UserDAO();
		String json=null;
		try {
			if(userdao.ifRight(user)){
				request.getSession().setAttribute("user", user);
				json="{\"flag\":true}";			
				System.out.println("µÇÂ¼³É¹¦");
			}else{
				json="{\"flag\":false}";
				System.out.println("µÇÂ¼Ê§°Ü");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out=response.getWriter();
		out.println(json);
		System.out.println(request.getSession().getAttribute("user"));
	}

	
	public void init() throws ServletException {
	}

}
