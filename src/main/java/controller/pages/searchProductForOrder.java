package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class searchProductForOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDao.validateProduct(name)){
                //если такой продукт есть, то выводим информацию о нем
                ProductsDao.getOneProduct(name);
                request.setAttribute("products", ProductsDao.getOneProduct(name));
                //ToDo сделать страницу с отображением найденого продукта справа под поисковиком
                answer ="#";
            } else {
                answer ="/WEB-INF/user-basic/merchandiser_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
