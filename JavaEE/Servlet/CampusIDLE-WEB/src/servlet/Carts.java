package servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemsDAO;
import dao.CartDAO;
import entity.Cart;
import entity.Items;
import entity.User;
import util.DBhelper;
@WebServlet("/cart")
public class Carts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action ;
	
	
	public Carts() {
		super();
	}
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		ServletContext sc=this.getServletContext();
		DBhelper dc=(DBhelper) sc.getAttribute("dbhelper");
		CartDAO cartdao=new CartDAO(dc);//�����������ﳵdao��Ķ���
		ItemsDAO itemsdao=new ItemsDAO(dc);//����������Ʒdao��Ķ���
		int uid=((User)request.getSession().getAttribute("user")).getId();
		int id = 1;
		if(request.getParameter("id")!=null)
			id = Integer.parseInt(request.getParameter("id"));
		
		if(request.getSession().getAttribute("cart")==null){//����ǵ�һ�η��ʹ��ﳵ���򴴽����ﳵ����
			Cart cart = new Cart();
			cart.setGoods(cartdao.getAllItemsFromCart(uid));
			request.getSession().setAttribute("cart",cart);
		}
		
		if(request.getParameter("action")!=null){
			this.action = request.getParameter("action");
			if(action.equals("add")){ //���
				if(addToCart(request,response,cartdao,itemsdao,uid,id)){
					response.sendRedirect("details.jsp?id="+request.getParameter("id")+"&Page="+request.getParameter("Page")+"&flag=true");
				}else{
					response.sendRedirect("details.jsp?id="+request.getParameter("id")+"&Page="+request.getParameter("Page")+"&flag=false");
				}
			}else if(action.equals("show")){//�鿴���ﳵ
				response.sendRedirect("firstPage.jsp?div=2");
			}else if(action.equals("delete")){ //ɾ��
				if(deleteFromCart(request,response,cartdao,itemsdao,uid,id)){
					response.sendRedirect("firstPage.jsp?div=2"+"&flag=true");
				}else{
					response.sendRedirect("firstPage.jsp?div=2"+"&flag=false");
				}
			}else if(action.equals("clear")){//��չ��ﳵ
				if(clearCart(request,response,cartdao,itemsdao,uid)){
					response.sendRedirect("firstPage.jsp?div=2"+"&flag=true");
				}else{
					response.sendRedirect("firstPage.jsp?div=2"+"&flag=false");
				}
			}
		}
	}

	//�����Ʒ�����ﳵ
	private boolean addToCart(HttpServletRequest request, HttpServletResponse response,CartDAO cartdao,ItemsDAO itemsdao,int uid,int id) throws IOException{
		Items item = itemsdao.getItemsById(id);
		item.setPage(Integer.parseInt(request.getParameter("Page")));
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart.addGoodToCart(item)){
			if(cartdao.addGoodToCart(id,uid)){
				return true;
			}
		}
		return false;
	}
	
	//�ӹ��ﳵ��ɾ����Ʒ
	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response,CartDAO cartdao,ItemsDAO itemsdao,int uid,int id){
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Items item = itemsdao.getItemsById(id);
	    if(cart.removeGoodsFromCart(item)){
			if(cartdao.removeGoodsFromCart(id,uid)){
				return true;
			}
	    }
	    return false;
	}
	
	//��չ��ﳵ
	private boolean clearCart(HttpServletRequest request, HttpServletResponse response,CartDAO cartdao,ItemsDAO itemsdao,int uid){
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    if(cart.clearCart()){
			if(cartdao.clearCart(uid)){
				return true;
			}
	    }
	    return false; 
	}
	public void init() throws ServletException{
	}

}
