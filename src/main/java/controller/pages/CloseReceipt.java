package controller.pages;

import controller.command.Command;
import db.BasketDAO;
import db.GoodsArchiveDAO;
import db.ProductsDAO;
import db.ReceiptsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * RU: закрытие текущего чека и сохранение всех продуктов
 * этого чека из корзины в архиве товаров
 * ENG: closing the current check and saving all the products
 * of this check from the basket in the archive of goods
 */
public class CloseReceipt implements Command {
    private static final Logger log = Logger.getLogger(CloseReceipt.class.getName());

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        GoodsArchiveDAO.saveBacket(ReceiptsDAO.getLastReceiptId());
        request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
        request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
        request.setAttribute("basket", BasketDAO.getAllBasket());
        request.setAttribute("products", ProductsDAO.getAllProducts());

        if (BasketDAO.checkBasket()) {
            log.error("Last receipt hasn't been closed");
            return "/WEB-INF/user-basic/errorClosedReceipt.jsp";
        } else {
            log.info("Last Receipt has been closed successfully");
            return "/WEB-INF/user-basic/successfullyClosedReceipt.jsp";
        }
    }

    @Override
    public String toString() {
        return "CloseReceipt";
    }
}