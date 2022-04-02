package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class searchReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer = null;
        String search=request.getParameter("search");
     //  int idreceipt = Integer.parseInt(request.getParameter("idreceipt"));
        System.out.println("search: "+search);

        System.out.println("ReceipsDAO: "+ReceipsDAO.isNumeric(search));
        if(ReceipsDAO.isNumeric(search)){
            int idreceipt=  Integer.parseInt(search);
            //TODO поиск по id заданого продукта и его вывод с кнопкой изменить или удалить
            if(ReceipsDAO.validateReceipt(idreceipt)){
                request.setAttribute("receipts",  ReceipsDAO.getReceiptsByID(idreceipt));
                answer ="/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else{answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";}
        } else{
            String cashier_name=search;
            //TODO поиск по cashier_name заданого продукта и его вывод с кнопкой изменить или удалить
            if(ReceipsDAO.validateReceipt(cashier_name)){
                request.setAttribute("receipts",  ReceipsDAO.getReceiptsByCashierName(cashier_name));
                answer ="/WEB-INF/st_cashier-basic/changeReceipt.jsp";
            } else{answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";}
        }
        return answer;
    }
}
