package controller.command;

import db.ProductsDao;
import db.UserDao;
import db.entity.User;
import db.enums.UserRole;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        //username and password:

        String name = request.getParameter("username");
        String pass = request.getParameter("password");
      //  System.out.println(name+ pass);
        String answer = null;
        //System.out.println(UserDao.validate(name, pass));
        if (UserDao.validate(name, pass)) {
            if (UserDao.getUser(name, pass).equals(UserRole.merchandiser.toString())) {
               // CommandUtility.setUserRole(request, UserRole.merchandiser, name);

                answer = "redirect:/adminhello";
            } else if (UserDao.getUser(name, pass).equals(UserRole.cashier.toString())) {
               CommandUtility.setUserRole(request, UserRole.cashier, name);
                answer = "redirect:/userhello";
            } else if(UserDao.getUser(name, pass).equals(UserRole.st_cashier.toString())){
               // CommandUtility.setUserRole(request, UserRole.st_cashier, name);
                answer = "redirect:/st_cashier";
            }
        } else {
           // CommandUtility.setUserRole(request, UserRole.unknown, name);
            answer = "/index.jsp";
        }
        return answer;
    }
}

   /* public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n = request.getParameter("username");
        String p = request.getParameter("password");
        System.out.println(n);
        System.out.println(p);
        if(UserDao.validate(n, p)){
            if(UserDao.getUser(n, p).equals(UserRole.merchandiser.toString())){
                //2 строки рабочего форварда
                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/admin-basic/adminhello.jsp");
                rd.forward(request,response);

                //попытка сделать редирект не привела к успеху
               // response.sendRedirect(request.getContextPath() + "/WEB-INF/admin-basic/adminhello.jsp");

            } else if(UserDao.getUser(n, p).equals(UserRole.cashier.toString())){
                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/user-basic/userhello.jsp");
                rd.forward(request,response);
            } else if(UserDao.getUser(n, p).equals(UserRole.st_cashier.toString())){
                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/st_cashier-basic/st_cashier.jsp");
                rd.forward(request,response);
            }
        }
        else{
            out.print("Sorry username or password error");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request,response);
        }
        out.close();



    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Way way = new Way();
        String n = request.getParameter("username");
        String p = request.getParameter("password");
        response.sendRedirect(way.wayLogin(n, p).replace("redirect:", ""));

    }*/
