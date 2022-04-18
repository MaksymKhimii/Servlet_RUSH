package controller.pages;

import controller.command.Command;
import db.ProductsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка изменения продукта в базе данных
 * ENG: processing product change in database
 */
public class ChangeProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));
            System.out.println("name="+name);
            System.out.println("quantity="+quantity);
            System.out.println("weight="+weight);
            System.out.println("tonnage "+tonnage);
            System.out.println("price = "+price);
            ProductsDAO.changeProduct(name, quantity, weight, tonnage, price);
            if(ProductsDAO.validateProduct(name, quantity, weight, tonnage, price)){
                answer="/WEB-INF/admin-basic/successfully_changed.jsp";
            } else{
                answer="/WEB-INF/admin-basic/danger_not_changed.jsp";
            }
            request.setAttribute("products", ProductsDAO.getAllProducts());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
