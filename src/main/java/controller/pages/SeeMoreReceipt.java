package controller.pages;

import controller.command.Command;
import db.ReceiptsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка отображения и взаимодействия
 *      с полной информацие о чеке и его продуктах
 * ENG: processing of display and interaction
 *      with full information about the check and its products
 */
public class SeeMoreReceipt implements Command {
    static int idreceipt;
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        String answer=null;
        try {
             idreceipt= Integer.parseInt(request.getParameter("idreceipt"));
             if(ReceiptsDAO.validateProductsOfReceipt(idreceipt)){
                 ReceiptsDAO.getReceiptsProdByID(idreceipt);
                 request.setAttribute("rec", idreceipt);
                 request.setAttribute("receipt",  ReceiptsDAO.getReceiptsProdByID(idreceipt));
                 request.setAttribute("SUM", ReceiptsDAO.getReceiptSum(idreceipt));
                 answer="/WEB-INF/st_cashier-basic/seeMoreReceipt.jsp";
             } else{
                 request.setAttribute("rec", idreceipt);
                 request.setAttribute("receipt",  ReceiptsDAO.getReceiptsProdByID(idreceipt));
                 request.setAttribute("SUM", ReceiptsDAO.getReceiptSum(idreceipt));
                 answer="/WEB-INF/st_cashier-basic/ReceiptProdError.jsp";
             }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
    public static int GetIdReceipt(){
        return idreceipt;
    }
}


