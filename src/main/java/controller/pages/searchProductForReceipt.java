package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDAO;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка поиска продукта для дальнейшего добавления его в корзину
 * ENG: processing the search for a product to further add it to the cart
 */
public class searchProductForReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        try {
            String name = request.getParameter("name");
            if(ProductsDAO.validateProduct(name)){
                ProductsDAO.getOneProduct(name);
                request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
                request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
                request.setAttribute("basket", BasketDAO.getAllBasket());
                request.setAttribute("products",  ProductsDAO.getOneProduct(name));
                if(BasketDAO.checkBasket()){
                    answer ="/WEB-INF/user-basic/addToReceipt.jsp";
                }else{
                        answer="/WEB-INF/user-basic/firstAddProduct.jsp";
                }
            } else {
                answer = "/WEB-INF/user-basic/cashier_error.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
