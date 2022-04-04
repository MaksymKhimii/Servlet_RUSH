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

            if(BasketDAO.addProdToBasket(idproduct, name, quantity, weight, tonnage, price)){
                //TODO изменить общую сумму чека!!!
                ReceipsDAO.addReceiptSum(ReceipsDAO.getLastReceiptId(),
                        BasketDAO.countSumOneProduct(idproduct,name, quantity, weight, tonnage, price));
                request.setAttribute("rec", ReceipsDAO.getLastReceiptId()); //отображение id чека
                request.setAttribute("totalSum", ReceipsDAO.getReceiptSum(ReceipsDAO.getLastReceiptId()));
                request.setAttribute("basket", BasketDAO.getAllBasket());
                request.setAttribute("products", ProductsDao.getAllProducts());
                answer ="/WEB-INF/user-basic/successfullyAddedToBasket.jsp";
            } else if(BasketDAO.checkBasket()) {
                answer ="/WEB-INF/user-basic/cashier_error.jsp";
            } else{
                answer ="/WEB-INF/user-basic/cashier_first_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;

    }
}
