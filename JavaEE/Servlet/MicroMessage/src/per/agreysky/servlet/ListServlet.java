package per.agreysky.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.agreysky.bean.Message;
import per.agreysky.service.QueryService;

@WebServlet("/List.action")
public class ListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String command = request.getParameter("command");
        String description = request.getParameter("description");
        QueryService queryService = new QueryService();
        List<Message> messageList = queryService.queryMessageList(command,
                description);
        request.setAttribute("messageList", messageList);
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
