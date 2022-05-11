package controller.pages;

import controller.command.Command;
import db.ProductsDAO;
import db.ReceiptsDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/** RU: создание нового чека
 * ENG: creating a new receipt
 */
public class CreateReceipt implements Command {
    private static final Logger log = Logger.getLogger(CreateReceipt.class.getName());
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession(); // тут вытягиваем сессию
        String cashier_id = (String)session.getAttribute("username"); // тут получаем текущего кассира
        ReceiptsDAO.addReceipt(cashier_id); // создаем новый чек

        request.setAttribute("rec", ReceiptsDAO.getLastReceiptId());
        request.setAttribute("totalSum", ReceiptsDAO.getReceiptSum(ReceiptsDAO.getLastReceiptId()));
        request.setAttribute("products", ProductsDAO.getAllProducts());
        log.info("Receipt: "+ReceiptsDAO.getLastReceiptId()+" has been created");
        return "/WEB-INF/user-basic/createReceipt.jsp";
    }

    @Override
    public String toString() {
        return "create_Receipt";
    }
}
