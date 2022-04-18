package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class searchReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        String search=request.getParameter("search");
     //  int idreceipt = Integer.parseInt(request.getParameter("idreceipt"));

        if(ReceiptsDAO.isNumeric(search)){
            int idreceipt=  Integer.parseInt(search);

            if(ReceiptsDAO.validateReceipt(idreceipt)){
                request.setAttribute("receipts",  ReceiptsDAO.getReceiptsByID(idreceipt));
                answer ="/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else{answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";}
        } else{
            String cashier_name=search;
            if(ReceiptsDAO.validateReceipt(cashier_name)){
                request.setAttribute("receipts",  ReceiptsDAO.getReceiptsByCashierName(cashier_name));
                answer ="/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else{answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";}
        }
        return answer;
    }
}
