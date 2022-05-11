package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка удаления продукта из базы данных
 * ENG: processing the removal of a product from the database
 */
public class DeleteProduct implements Command {
    private static final Logger log = Logger.getLogger(DeleteProduct.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDAO.deleteProduct(name)){
                log.info("Product "+name+" has been deleted");
                answer="/WEB-INF/admin-basic/successfully_deleted.jsp";
            } else{
                log.error("Product "+name+" hasn't been deleted");
                answer ="/WEB-INF/common/merchandiser_error.jsp";
            }
            request.setAttribute("products", ProductsDAO.getAllProducts());
        } catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public String toString() {
        return "deleteProduct";
    }
}
