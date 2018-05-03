package per.agreysky.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.agreysky.service.MaintainService;

@WebServlet("/DeleteOneServlet.action")
public class DeleteOneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteOneServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        MaintainService maintainService = new MaintainService();
        maintainService.deleteOne(id);
        request.getRequestDispatcher("/List.action").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
