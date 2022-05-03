package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка поиска продукта по наименованию в базе данных
 * ENG: processing the search for a product by name in the database
 */
public class searchProduct implements Command {
    private static final Logger log = Logger.getLogger(searchProduct.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDAO.validateProduct(name)){
                ProductsDAO.getOneProduct(name);
                log.debug("Product "+name+" was found");
                request.setAttribute("products", ProductsDAO.getOneProduct(name));
                answer ="/WEB-INF/admin-basic/change_product.jsp";
            } else {
                log.debug("Product "+name+" wasn't found");
                answer ="/WEB-INF/admin-basic/merchandiser_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String toString() {
        return "searchProduct";
    }
}
