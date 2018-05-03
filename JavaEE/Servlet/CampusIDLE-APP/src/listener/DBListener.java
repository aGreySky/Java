package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import util.DBhelper;


@WebListener
public class DBListener implements ServletContextListener {  
    public DBListener() {}
    public void contextDestroyed(ServletContextEvent arg0)  {}
    
    
    public void contextInitialized(ServletContextEvent event)  { //读取上下文参数，并将连接数据库对象存入上下文
    	ServletContext sc=event.getServletContext();
        String driver=sc.getInitParameter("driver");
		String url=sc.getInitParameter("url");
		String username=sc.getInitParameter("username");
		String password=sc.getInitParameter("pwd");
		DBhelper dc=new DBhelper(driver,url,username,password);
		sc.setAttribute("dbhelper", dc);
    }
	
}
