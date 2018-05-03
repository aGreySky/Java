package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import dao.messageDAO;


@WebServlet("/message")
public class message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public message() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		messageDAO md=new messageDAO();
		md.addMessage(name, title, content);
		request.setAttribute("tishi", "µã»÷²é¿´ÁôÑÔ");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
