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

@WebServlet("/users/delete")
public class UsersDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserService.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(userService.getUserById(id));
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
