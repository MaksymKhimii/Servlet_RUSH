package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class searchProduct implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDao.validateProduct(name)){
                //если такой продукт есть, то выводим информацию о нем
                ProductsDao.getOneProduct(name);
                request.setAttribute("products", ProductsDao.getOneProduct(name));
                answer ="/WEB-INF/admin-basic/change_product.jsp";
            } else {
                answer ="/WEB-INF/common/merchandiser_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
