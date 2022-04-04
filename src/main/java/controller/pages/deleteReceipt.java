package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class deleteReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;
        try {
        int idreceipt= Integer.parseInt(request.getParameter("idreceipt"));
            System.out.println("idreceipt: "+idreceipt);
        if(ReceipsDAO.deleteReceipt(idreceipt)){
            answer="/WEB-INF/st_cashier-basic/successfullyDeletedReceipt.jsp"; // успешно удалено
        } else {
            answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
        }
        request.setAttribute("receipts", ReceipsDAO.getAllReceipts());
        } catch (NumberFormatException | SQLException e){
            e.printStackTrace();
        }
        return answer;
    }
}
