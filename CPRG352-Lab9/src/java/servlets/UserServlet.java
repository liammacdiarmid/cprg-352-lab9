package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;

public class UserServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException {
    UserService ns = new UserService();
    RoleService rs = new RoleService();

    try {
      List<Role> roles = rs.getAll();
      request.setAttribute("roleLabel", roles);

      List<User> users = ns.getAll();
      request.setAttribute("users", users);
    } catch (Exception ex) {}

    getServletContext()
      .getRequestDispatcher("/WEB-INF/users.jsp")
      .forward(request, response);
    return;
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  )
    throws ServletException, IOException {
    HttpSession session = request.getSession();

    String action = request.getParameter("action");

    String toDelete = request.getParameter("toDelete");
    String toUpdate = request.getParameter("toEdit");

    String email = request.getParameter("email");
    boolean active = true;
    String first_name = request.getParameter("firstName");
    String last_name = request.getParameter("lastName");
    String password = request.getParameter("password");
    int role = 0;

    UserService us = new UserService();
    RoleService rs = new RoleService();

    try {
      switch (action) {
        case "add":
          role = Integer.parseInt(request.getParameter("roleSelect"));
          us.insert(email, active, first_name, last_name, password, role);
          break;
        case "delete":
          us.delete(toDelete);
          break;
        case "edit":
          User toUpdateUser = us.get(toUpdate);
          request.setAttribute("emailEdit", toUpdateUser.getEmail());
          request.setAttribute("firstNameEdit", toUpdateUser.getFirstName());
          request.setAttribute("lastNameEdit", toUpdateUser.getLastName());
          request.setAttribute("passwordEdit", toUpdateUser.getPassword());
          request.setAttribute("roleEdit", toUpdateUser.getRole().getRoleId());
          break;
        case "save":
          email = request.getParameter("emailEdit");
          first_name = request.getParameter("firstNameEdit");
          last_name = request.getParameter("lastNameEdit");
          password = request.getParameter("passwordEdit");
          role = Integer.parseInt(request.getParameter("roleEdit"));
          User updatedUser = new User(
            email,
            active,
            first_name,
            last_name,
            password,
            role
          );
          us.update(updatedUser);
          break;
        default:
          getServletContext()
            .getRequestDispatcher("/WEB-INF/users.jsp")
            .forward(request, response);
      }
    } catch (Exception ex) {
      Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
      request.setAttribute("message", "error");
    }

    try {
      List<User> users = us.getAll();
      request.setAttribute("users", users);
      List<Role> roles = rs.getAll();
      request.setAttribute("roleLabel", roles);
    } catch (Exception ex) {}

    getServletContext()
      .getRequestDispatcher("/WEB-INF/users.jsp")
      .forward(request, response);
    return;
  }
}
