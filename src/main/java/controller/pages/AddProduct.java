package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.sql.SQLException;

public class AddProduct implements Command {
    /**В этом переопределенном методе принимаются параметры из формы add_new_product.jsp и добавляются в БД.
     * Далее происходит валидация и перенаправление на страницу с сообщение о результате операции:
     * выполнено успешно или ошибка добавления*/
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
            request.setAttribute("products", ProductsDao.getAllProducts());
            if(ProductsDao.validateProduct(name)){
                //сообщение о том что такой продукт уже есть
                answer ="/WEB-INF/admin-basic/danger_not_added.jsp";
            } else if(ProductsDao.addProduct(name, quantity, weight, tonnage, price)){
                //сообщение о том что продукт успешно добавлен
                answer ="/WEB-INF/admin-basic/successfully_added.jsp";
            } else {
                answer ="/WEB-INF/admin-basic/danger_not_added.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
