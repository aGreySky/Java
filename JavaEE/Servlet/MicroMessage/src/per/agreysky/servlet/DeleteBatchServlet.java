package per.agreysky.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.agreysky.service.MaintainService;

@WebServlet("/DeleteBatchServlet.action")
public class DeleteBatchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteBatchServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String[] ids = request.getParameterValues("id");
        MaintainService maintainService = new MaintainService();
        maintainService.deleteBatch(ids);
        request.getRequestDispatcher("/List.action").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
