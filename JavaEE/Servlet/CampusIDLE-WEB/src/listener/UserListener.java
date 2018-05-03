package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserListener implements HttpSessionListener {
	
	private int userNumber;
	
    public void sessionCreated(HttpSessionEvent arg0)  { 
         userNumber++;
         arg0.getSession().getServletContext().setAttribute("userNumber", userNumber);
    }

	
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
    	userNumber--;
    	arg0.getSession().getServletContext().setAttribute("userNumber", userNumber);
    }
	
}