package controller;

import model.UserService;
import modelEntity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
       // userService.createTable();
       // userService.addUser(new User("adad", "adad"));
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/list.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UserService userService = UserService.getInstance();
        User user = new User(userService.configNewId(), name, password);
        if (userService.addUser(user)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        resp.sendRedirect("users");
    }
}
