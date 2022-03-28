package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDao;
import db.ReceipsDAO;
import db.entity.Basket;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

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
            int idproduct= Integer.parseInt(request.getParameter("idproducts"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));
            System.out.println("idproducts:"+idproduct);
            System.out.println("name"+name);
            System.out.println("quantity"+quantity);
            System.out.println("weight"+weight);
            System.out.println("tonnage"+tonnage);
            System.out.println("price"+price);
            //добавление продукта в корзину нового чека
            BasketDAO.addProdToBasket(idproduct, name, quantity, weight, tonnage, price);

            request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
            request.setAttribute("basket", BasketDAO.getAllBasket());
            request.setAttribute("products", ProductsDao.getAllProducts());
            if(BasketDAO.validateBasket(idproduct, name, quantity, weight, tonnage, price)){
                //TODO сделать сообщение или всплывающее окно
                // об успешном добавлении продукта
                // тут будет отображаться 2 таблицы с продолжением поиска продуктов
                answer ="/WEB-INF/user-basic/duringReceipt.jsp";
            } else{
                //TODO сообщение об ошибке/ страница об ошибке
                answer="#";
            }


        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;

    }
}
