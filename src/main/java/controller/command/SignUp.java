package controller.command;

import db.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/common/sign-up.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        System.out.println(username);
        System.out.println(password);
        System.out.println(role);
        try {
            if (UserDAO.validate(username, password)) {
                request.getRequestDispatcher("/WEB-INF/common/sign-up.jsp").forward(request, response);
            } else {
                if (UserDAO.addUser(username, password, role)){
                    request.getRequestDispatcher("/Login").forward(request, response);
                }
            }
        } catch (SQLException | ClassNotFoundException | ServletException e){
            e.printStackTrace();
        }
        out.close();
    }



}