package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeeMoreReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать страницу с отображением всех
        // продуктов одного чека и возможностью их удаления, удааления всего чека
        String answer=null;
        try {
            int idreceipt= Integer.parseInt(request.getParameter("idreceipt"));
            System.out.println("idreceipt: "+idreceipt);
            ReceipsDAO.getReceiptsProdByID(idreceipt);
            request.setAttribute("rec", idreceipt);//выводим idreceipt продукты которого будем изменять
            request.setAttribute("receipt",  ReceipsDAO.getReceiptsProdByID(idreceipt));//выводим данные о одном продукте
            request.setAttribute("SUM", ReceipsDAO.getReceiptSum(idreceipt)); //выводим сумму за этот чек
            answer="/WEB-INF/st_cashier-basic/seeMoreReceipt.jsp";

        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}

