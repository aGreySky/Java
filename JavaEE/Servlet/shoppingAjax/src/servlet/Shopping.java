package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.CartDAO;
import dao.ItemDAO;
import entity.Cart;
import entity.Items;


@WebServlet("/Shopping")
public class Shopping extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Shopping() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int id=Integer.parseInt(request.getParameter("id"));
				ItemDAO itemdao=new ItemDAO();
				Items item=itemdao.getItemById(id);//通过商品操作类获取到商品对象
				CartDAO cartdao=new CartDAO();
		if(request.getParameter("action").equals("add")){//添加
			if(request.getSession().getAttribute("cart")==null){
				Cart cart=new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.addItemToCart(cart, item);//通过购物车操作类将商品存入购物车
			response.sendRedirect("paging?Page=1");
		}
		if(request.getParameter("action").equals("delete")){//删除
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.DeleteItem(cart, item);//通过购物车操作类将商品从购物车中删除
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
