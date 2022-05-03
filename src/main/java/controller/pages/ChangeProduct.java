package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка изменения продукта в базе данных
 * ENG: processing product change in database
 */
public class ChangeProduct implements Command {
    private static final Logger log = Logger.getLogger(ChangeProduct.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));

            ProductsDAO.changeProduct(name, quantity, weight, tonnage, price);
            if(ProductsDAO.validateProduct(name, quantity, weight, tonnage, price)){
                log.debug("Product "+name+" has been changed successfully");
                answer="/WEB-INF/admin-basic/successfully_changed.jsp";
            } else{
                log.error("Product hasn't been changed");
                answer="/WEB-INF/admin-basic/danger_not_changed.jsp";
            }
            request.setAttribute("products", ProductsDAO.getAllProducts());
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String toString() {
        return "ChangeProduct";
    }
}
