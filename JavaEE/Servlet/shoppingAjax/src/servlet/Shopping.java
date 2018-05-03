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
				Items item=itemdao.getItemById(id);//ͨ����Ʒ�������ȡ����Ʒ����
				CartDAO cartdao=new CartDAO();
		if(request.getParameter("action").equals("add")){//���
			if(request.getSession().getAttribute("cart")==null){
				Cart cart=new Cart();
				request.getSession().setAttribute("cart", cart);
			}
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.addItemToCart(cart, item);//ͨ�����ﳵ�����ཫ��Ʒ���빺�ﳵ
			response.sendRedirect("paging?Page=1");
		}
		if(request.getParameter("action").equals("delete")){//ɾ��
			Cart cart=(Cart)request.getSession().getAttribute("cart");
			cartdao.DeleteItem(cart, item);//ͨ�����ﳵ�����ཫ��Ʒ�ӹ��ﳵ��ɾ��
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
