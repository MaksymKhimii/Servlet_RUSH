package controller.pages;

import controller.command.Command;
import db.ProductsDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AddToBasket implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO такс, здесь мы принимааем параменты продукта с указанием колличества его в чек
        // 1. эти данные надо добавить в таблицу basket с указанием idreceipt нового чека!!!
        // 2. также нужно учесть тот факт что колличество продукта нужно отнять от того что есть
        // в таблице products
        // 3. сделать проверку доступного веса весового продукта или же доступного колличества продукта на складе
        // (если на складе меньше чем нужно - отобразить предупреждение и вернуть на страницу добавления продукта в чек)
        // 4. Создать страницу отображения добавленого продукта слева на странице
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

        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;

    }
}
