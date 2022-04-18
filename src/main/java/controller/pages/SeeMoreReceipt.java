package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeeMoreReceipt implements Command {
    static int idreceipt;
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;
        try {
             idreceipt= Integer.parseInt(request.getParameter("idreceipt"));
            ReceiptsDAO.getReceiptsProdByID(idreceipt);
            request.setAttribute("rec", idreceipt);//выводим idreceipt продукты которого будем изменять

            request.setAttribute("receipt",  ReceiptsDAO.getReceiptsProdByID(idreceipt));//выводим данные о одном продукте
            request.setAttribute("SUM", ReceiptsDAO.getReceiptSum(idreceipt)); //выводим сумму за этот чек
            answer="/WEB-INF/st_cashier-basic/seeMoreReceipt.jsp";

        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
    public static int GetIdReceipt(){
        return idreceipt;
    }
}


