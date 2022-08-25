package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * RU: обработка поиска чека по его id для дальнейшего взаимодействия с ним
 * ENG: processing check search by its id for further interaction with it
 */
public class SearchReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer;
        String search = request.getParameter("search");
        if (ReceiptsDAO.isNumeric(search)) {
            int idreceipt = Integer.parseInt(search);
            if (ReceiptsDAO.validateReceipt(idreceipt)) {
                request.setAttribute("receipts", ReceiptsDAO.getReceiptsByID(idreceipt));
                answer = "/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else {
                answer = "/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
            }
        } else {
            if (ReceiptsDAO.validateReceipt(search)) {
                request.setAttribute("receipts", ReceiptsDAO.getReceiptsByCashierName(search));
                answer = "/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else {
                answer = "/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
            }
        }
        return answer;
    }

    @Override
    public String toString() {
        return "searchReceipt";
    }
}
