package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class deleteProduct implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDao.deleteProduct(name)){
                //ToDo сообщение:успешно удалено(страница)
                answer="/WEB-INF/admin-basic/successfully_deleted.jsp";
            } else{
                answer ="/WEB-INF/common/error.jsp";
            }
            request.setAttribute("products", ProductsDao.getAllProducts());
        } catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        return answer;
    }
}
