package per.agreysky.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.agreysky.service.QueryService;

@WebServlet("/AutoReplyServlet.action")
public class AutoReplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AutoReplyServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        QueryService queryService = new QueryService();
        out.write(queryService.queryByName(request.getParameter("content")));
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
