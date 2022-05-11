package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.ProductsDAO;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка отображения информации
 *      об открытом чеке и корзине  продуктами этого чека
 * ENG: processing the display of information about
 *      an open receipt and a basket of products of this receipt
 */
public class DuringReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
        request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDAO.getAllProducts());
        return "/WEB-INF/user-basic/duringReceipt.jsp";
    }

    @Override
    public String toString() {
        return "duringReceipt";
    }
}
