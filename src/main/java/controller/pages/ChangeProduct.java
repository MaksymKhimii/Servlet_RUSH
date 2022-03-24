package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ChangeProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;

        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            double price = Double.parseDouble(request.getParameter("price"));
            System.out.println("name="+name);
            System.out.println("quantity="+quantity);
            System.out.println("weight="+weight);
            System.out.println("price = "+price);
            ProductsDao.changeProduct(name, quantity, weight, price);
            if(ProductsDao.validateProduct(name, quantity, weight, price)){
                answer="/WEB-INF/admin-basic/successfully_changed.jsp";
            } else{
                answer="/WEB-INF/admin-basic/danger_not_changed.jsp";
            }

            request.setAttribute("products", ProductsDao.getAllProducts());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
