package servlets;

import controller.command.*;
import controller.pages.*;
import controller.pages.merchandiser;
import controller.pages.cashier;

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
        commands.put("merchandiser", new merchandiser());
        commands.put("cashier", new cashier());
        commands.put("st_cashier", new st_cashier());
        commands.put("error", new merchandiser_error());
        commands.put("add_new_product", new add_new_product());
        commands.put("addProduct", new AddProduct());
        commands.put("searchProduct", new searchProduct());
        commands.put("changeProduct", new ChangeProduct());
        commands.put("deleteProduct", new deleteProduct());
        commands.put("searchProductForReceipt", new searchProductForReceipt());
        commands.put("create_Receipt", new create_Receipt());
        commands.put("AddToBasket", new AddToBasket());
        commands.put("duringReceipt", new duringReceipt());
        commands.put("closeReceipt", new CloseReceipt());
        commands.put("searchReceipt", new searchReceipt());
        commands.put("deleteReceipt", new deleteReceipt());
        commands.put("SeeMoreReceipt", new  SeeMoreReceipt());
        commands.put("deleteProductFromReceipt", new deleteProductFromReceipt());
        commands.put("Xreport", new Xreport());
        commands.put("report", new report());
        commands.put("AllReports", new AllReports());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        String path=request.getRequestURI();
        path=path.replaceAll(".*/", "");
        Command command = commands.getOrDefault(path,
                (r)->"/index.jsp");
        String page = command.execute(request);

        if(page.contains("redirect:") || page.contains("logout")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
