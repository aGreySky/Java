package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import entity.Items;
import tools.Pages;


@WebServlet("/paging")
public class paging extends HttpServlet {
	Pages myPages=null;
	ArrayList<Items> newList=null;
	private static final long serialVersionUID = 1L;
    public paging() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strPage=request.getParameter("Page");
		int Page=1;
		if(request.getSession().getAttribute("myPages")==null){
			myPages=new Pages();
			ItemDAO itemdao=new ItemDAO();
			ArrayList<Items> list=itemdao.getAllItems();
    		int recordCount=list.size();
    		int pagesize=4;
    		int maxPage=myPages.getMaxPage();
    		myPages.setList(list);
    		myPages.setMaxPage(maxPage);
    		myPages.setRecordCount(recordCount);
			newList=myPages.getInitPage(list, 1, pagesize);//初始化分页信息，即获取到存在数据库中的list,同时获取到首页数据
			request.getSession().setAttribute("myPages", myPages);
		}else{
			myPages=(Pages) request.getSession().getAttribute("myPages");
			Page=myPages.getPage(strPage);
			newList=myPages.getAppointPage(Page);//获取当前页数据
		}
		request.getSession().setAttribute("ItemsList", newList);
		request.getSession().setAttribute("Page", Page);
		request.setAttribute("url", "paging");
		response.sendRedirect("first.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
