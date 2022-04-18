package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.GoodsArchiveDAO;
import db.ProductsDAO;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: закрытие текущего чека и сохранение всех продуктов
 *      этого чека из корзины в архиве товаров
 * ENG: closing the current check and saving all the products
 *      of this check from the basket in the archive of goods
 */
public class CloseReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        GoodsArchiveDAO.saveBacket(ReceiptsDAO.getLastReceiptId());
        request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
        request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDAO.getAllProducts());

        if(BasketDAO.checkBasket()){
            return "/WEB-INF/user-basic/errorClosedReceipt.jsp";
        } else{
            return "/WEB-INF/user-basic/successfullyClosedReceipt.jsp";
        }

    }
}
