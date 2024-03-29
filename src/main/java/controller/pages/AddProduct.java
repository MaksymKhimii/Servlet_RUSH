package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * RU: В этом классе принимаются параметры из формы add_new_product.jsp и добавляются в базу данных .
 * Далее происходит валидация и перенаправление на страницу с сообщение о результате операции:
 * выполнено успешно или ошибка добавления
 * ENG: This class takes parameters from the add_new_product.jsp form and adds them to the database.
 * Next, validation occurs and a redirect to a page with a message about the result of the operation:
 * completed successfully or adding error
 */
public class AddProduct implements Command {
    private static final Logger log = Logger.getLogger(AddProduct.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage = Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));

            request.setAttribute("products", ProductsDAO.getAllProducts());
            if (ProductsDAO.validateProduct(name)) {
                log.error("This product already exists");
                answer = "/WEB-INF/admin-basic/danger_not_added.jsp";
            } else if (ProductsDAO.addProduct(name, quantity, weight, tonnage, price)) {
                log.info("New product " + name + " has been added");
                answer = "/WEB-INF/admin-basic/successfully_added.jsp";
            } else {
                log.error("Product hasn't been added. Its: " + name + ", " + quantity + ", " + weight + ", " + tonnage + ", " + price);
                answer = "/WEB-INF/admin-basic/danger_not_added.jsp";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String toString() {
        return "AddProduct";
    }
}
