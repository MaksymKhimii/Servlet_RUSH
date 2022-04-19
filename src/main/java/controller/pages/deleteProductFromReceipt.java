package controller.pages;

import controller.command.Command;

import db.GoodsArchiveDAO;
import db.ReceiptsDAO;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/** RU: обработка удаления конкретного продукта из закрытого чека
 * ENG: processing the removal of a specific product from a closed receipt
 */
public class deleteProductFromReceipt implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {

        String answer=null;
        try {
            int idreceipt=SeeMoreReceipt.GetIdReceipt();
            int idproduct= Integer.parseInt(request.getParameter("idproduct"));

            ReceiptsDAO.minusReceiptSum(idreceipt, GoodsArchiveDAO.getCostOneProduct(idreceipt, idproduct));
            GoodsArchiveDAO.updateSum(idreceipt);
            GoodsArchiveDAO.deleteProdFromReceipt(idproduct, idreceipt);
            if(!GoodsArchiveDAO.validateProdInReceipt(idproduct, idreceipt)){
               if(GoodsArchiveDAO.validateReceipt(idreceipt)){
                   answer="/WEB-INF/st_cashier-basic/successfullyDeletedProduct2.jsp";
               } else{
                   if(ReceiptsDAO.getReceiptSum(idreceipt)<=0){
                       ReceiptsDAO.deleteReceipt(idreceipt);
                   }
                   answer="/WEB-INF/st_cashier-basic/successfullyDeletedProduct2.jsp";
               }
            } else{
                answer="/WEB-INF/st_cashier-basic/receiptSearchError.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;
    }
}
