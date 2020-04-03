package servlet;

import service.UserServiceImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();
        List<User> users = userServiceImpl.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/list.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        requestDispatcher.forward(request, response);
    }
}
