package controller.pages;

import controller.command.Command;
import db.ProductsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка поиска продукта по наименованию в базе данных
 * ENG: processing the search for a product by name in the database
 */
public class searchProduct implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDAO.validateProduct(name)){
                ProductsDAO.getOneProduct(name);
                request.setAttribute("products", ProductsDAO.getOneProduct(name));
                answer ="/WEB-INF/admin-basic/change_product.jsp";
            } else {
                answer ="/WEB-INF/admin-basic/merchandiser_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
