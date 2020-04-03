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

@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/updateUser.jsp");
        response.setStatus(HttpServletResponse.SC_OK);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl service = UserServiceImpl.getInstance();
        int id = Integer.parseInt(req.getParameter("id"));
        User user = service.getUserById(id);
        if (user != null) {
            user.setName(req.getParameter("name"));
            user.setPassword(req.getParameter("password"));
            if (service.updateUser(user)) {
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
