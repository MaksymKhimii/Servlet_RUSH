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
        // 2. также нужно учесть тот факт что колличество продукта нужно отнять от того что есть
        // в таблице products
        // 3. сделать проверку доступного веса весового продукта или же доступного колличества продукта на складе
        // (если на складе меньше чем нужно - отобразить предупреждение и вернуть на страницу добавления продукта в чек)
        // 4. Создать страницу отображения добавленого продукта слева на странице
        String answer = null;
        try {
            int idproduct= Integer.parseInt(request.getParameter("idproducts"));
            System.out.println("idproduct: "+idproduct);
            String name = request.getParameter("name");
            System.out.println("name: "+name);
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            System.out.println("quantity: "+quantity);
            double weight = Double.parseDouble(request.getParameter("weight"));
            System.out.println("weight: "+weight);
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            System.out.println("tonnage: "+tonnage);
            double price = Double.parseDouble(request.getParameter("price"));
            System.out.println("price: "+price);
            //добавление продукта в корзину нового чека

           /* request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
            request.setAttribute("basket", BasketDAO.getAllBasket());
            request.setAttribute("products", ProductsDao.getAllProducts());*/
            if(BasketDAO.addProdToBasket(idproduct, name, quantity, weight, tonnage, price)){
                request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
                request.setAttribute("basket", BasketDAO.getAllBasket());
                request.setAttribute("products", ProductsDao.getAllProducts());
                answer ="/WEB-INF/user-basic/successfullyAddedToBasket.jsp";
            } else{
                System.out.println("Basket?:"+BasketDAO.addProdToBasket(idproduct, name, quantity, weight, tonnage, price));
                answer ="/WEB-INF/user-basic/cashier_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;

    }
}
