package servlet;

import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/delete")
public class UsersDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/deleteUser.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        userServiceImpl.deleteUser(userServiceImpl.getUserById(id));
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
