package controller.pages;



import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class st_cashier implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        request.setAttribute("receipts", ReceiptsDAO.getAllReceipts());
        return "/WEB-INF/st_cashier-basic/st_cashier.jsp";
    }
}
