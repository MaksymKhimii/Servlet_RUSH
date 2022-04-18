package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDAO;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка добавления нового продукта в корзину текущего чека
 * ENG: processing of adding a new product to the cart of the current check
 */
public class AddToBasket implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            int idproduct= Integer.parseInt(request.getParameter("idproducts"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double weight = Double.parseDouble(request.getParameter("weight"));
            boolean tonnage=Boolean.parseBoolean(request.getParameter("tonnage"));
            double price = Double.parseDouble(request.getParameter("price"));

            if(BasketDAO.addProdToBasket(idproduct, name, quantity, weight, tonnage, price)){
                ReceiptsDAO.addReceiptSum(ReceiptsDAO.getLastReceiptId(),
                        BasketDAO.countSumOneProduct(idproduct,name, quantity, weight, tonnage, price));
                request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
                request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
                request.setAttribute("basket", BasketDAO.getAllBasket());
                request.setAttribute("products", ProductsDAO.getAllProducts());
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
