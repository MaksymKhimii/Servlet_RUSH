package controller.pages;

import controller.command.Command;
import db.ProductsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: В этом классе принимаются параметры из формы add_new_product.jsp и добавляются в базу данных .
 *      Далее происходит валидация и перенаправление на страницу с сообщение о результате операции:
 *      выполнено успешно или ошибка добавления
 * ENG: This class takes parameters from the add_new_product.jsp form and adds them to the database.
 *      Next, validation occurs and a redirect to a page with a message about the result of the operation:
 *      completed successfully or adding error
 */
public class AddProduct implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));
            System.out.println("name"+name);
            System.out.println("quantity"+quantity);
            System.out.println("weight"+weight);
            System.out.println("tonnage"+tonnage);
            System.out.println("price"+price);
            request.setAttribute("products", ProductsDAO.getAllProducts());
            if(ProductsDAO.validateProduct(name)){
                //message that such a product already exists
                answer ="/WEB-INF/admin-basic/danger_not_added.jsp";
            } else if(ProductsDAO.addProduct(name, quantity, weight, tonnage, price)){
                //message that the product was added successfully
                answer ="/WEB-INF/admin-basic/successfully_added.jsp";
            } else {
                // error message
                answer ="/WEB-INF/admin-basic/danger_not_added.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
