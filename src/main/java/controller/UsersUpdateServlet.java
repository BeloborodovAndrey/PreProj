package controller;

import model.UserService;
import modelEntity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = UserService.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        User user = service.getUserById(id);
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        if (service.updateUser(user)) {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
