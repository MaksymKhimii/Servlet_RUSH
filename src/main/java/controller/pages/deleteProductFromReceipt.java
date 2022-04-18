package controller.pages;

import controller.command.Command;

import db.GoodsArchiveDAO;
import db.ReceiptsDAO;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class deleteProductFromReceipt implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {


        String answer=null;
        try {
            int idreceipt=SeeMoreReceipt.GetIdReceipt();
            System.out.println("idreceipt: "+idreceipt);
            int idproduct= Integer.parseInt(request.getParameter("idproduct"));
            System.out.println("idproduct: "+idproduct);

            //отнимаем сумму за продукт которій будем удалять
            ReceiptsDAO.minusReceiptSum(idreceipt, GoodsArchiveDAO.getCostOneProduct(idreceipt, idproduct));
            GoodsArchiveDAO.updateSum(idreceipt); //сохраняем новую сумму во второй таблице
            GoodsArchiveDAO.deleteProdFromReceipt(idproduct, idreceipt);//удаляем продукт
            if(!GoodsArchiveDAO.validateProdInReceipt(idproduct, idreceipt)){
               if(GoodsArchiveDAO.validateReceipt(idreceipt)){
                   answer="/WEB-INF/st_cashier-basic/successfullyDeletedProduct2.jsp";
               } else{
                   //если продуктов из этого чека не осталось
                   if(ReceiptsDAO.getReceiptSum(idreceipt)<=0){
                       ReceiptsDAO.deleteReceipt(idreceipt);
                   }
                   answer="/WEB-INF/st_cashier-basic/successfullyDeletedProduct2.jsp";

               }
            } else{
                answer="/WEB-INF/st_cashier-basic/ReceiptSearchError.jsp";
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return answer;

    }
}
