package servlets;

import controller.command.*;
import controller.pages.*;
import db.entity.User;
import db.entity.adminhello;
import db.entity.userhello;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();
    public void init(ServletConfig servletConfig){

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("logout", new Logout());
        commands.put("login", new Login());
        commands.put("exception" , new ExceptionCommand());
        commands.put("adminhello", new adminhello());
        commands.put("userhello", new userhello());
        commands.put("st_cashier", new st_cashier());
        //commands.put("signup", new signup());
        //commands.put("index", new index());
        commands.put("error", new error());
        commands.put("add_new_product", new add_new_product());
        commands.put("addProduct", new AddProduct());
        commands.put("searchProduct", new searchProduct());
        commands.put("changeProduct", new ChangeProduct());
        commands.put("deleteProduct", new deleteProduct());
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //response.getWriter().print("Hello from servlet");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
       /* String path = request.getRequestURI();
        Command command = commands.getOrDefault(path ,
                (r)->"/index.jsp");
        System.out.println(command.getClass().getName());
        String page = command.execute(request);*/
        String path=request.getRequestURI();
        path=path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path,
                (r)->"/index.jsp");
        //command.getClass().getName();
        String page = command.execute(request);

        if(page.contains("redirect:") || page.contains("logout")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
