package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDAO;
import dao.ItemDAO;
import entity.Cart;
import entity.Items;

/**
 * Servlet implementation class Shopping
 */
@WebServlet("/Shopping")
public class Shopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Shopping() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String id=request.getParameter("id");
				ItemDAO itemdao=new ItemDAO();
				Items item=itemdao.getItemById(id);
				CartDAO cartdao=new CartDAO();
		if(request.getParameter("action").equals("add")){//Ìí¼Ó
			if(request.getSession().getAttribute("cart")==null){
				Cart cart=new Cart();
				request.getSession().setAttribute("cart", cart);
				
			}
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.addItemToCart(cart, item);
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("delete")){//É¾³ý
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.DeleteItem(cart, item);
			request.getRequestDispatcher("/Cart.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
